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

    totalFee += transaction.getAmountTransferred() + transaction.getFee();

    SmartContractBase linkedSmartContract = transaction.getLinkedSmartContract();
    if (linkedSmartContract != null) {
      if (transaction.getType() == TransactionType.SMART_CONTRACT_DEPLOY) {
        totalFee += SmartContractExecutor.evaluateContractCost(linkedSmartContract);
      }

      if (transaction.getType() == TransactionType.SMART_CONTRACT_EXECUTE) {
        try {
          totalFee += SmartContractExecutor.evaluateMethodCost(linkedSmartContract, transaction.getMethodName());
        } catch (Exception e) {
          return false;
        }
      }
    }

    if (senderNode.getBalance() < totalFee)
      return false;

    if (this.nextHandler != null)
      this.nextHandler.validate(transaction, blockchain);

    return true;
  }

}
