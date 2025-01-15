package ValidationControls;

import Blockchain.Blockchain;
import Node.Node;
import SmartContract.SmartContractBase;
import SmartContract.SmartContractExecutor;
import Transaction.Transaction;
import Transaction.TransactionType;

public class BalanceSuffices extends ValidationHandler {

  @Override
  public boolean validate(Transaction transaction, Blockchain blockchain) {
    Integer totalFee = 0;

    String callerAddress = transaction.getCallerAddress();
    Node senderNode = blockchain.getNode(callerAddress);

    totalFee += transaction.getAmountToTransfer() + transaction.getFee();

    SmartContractBase linkedSmartContract = transaction.getLinkedSmartContract();
    if (linkedSmartContract != null) {
      if (transaction.getType() == TransactionType.SMART_CONTRACT_DEPLOY) {
        totalFee += SmartContractExecutor.evaluateContractCost(linkedSmartContract);
      }

      if (transaction.getType() == TransactionType.SMART_CONTRACT_EXECUTE) {
        try {
          totalFee += SmartContractExecutor.evaluateMethodCost(
              blockchain.getSmartContract(transaction.getLinkedSmartContractAddress()), transaction.getMethodName());
        } catch (Exception e) {
          return false;
        }
      }
    }

    if (senderNode.getBalance() < totalFee)
      return false;

    if (this.nextHandler != null)
      return this.nextHandler.validate(transaction, blockchain);

    else
      return true;

  }

}
