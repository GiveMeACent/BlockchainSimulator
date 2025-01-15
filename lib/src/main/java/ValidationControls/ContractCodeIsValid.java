package ValidationControls;

import java.util.List;

import Blockchain.Blockchain;
import Node.Node;
import SmartContract.SmartContractBase;
import SmartContract.SmartContractExecutor;
import Transaction.Transaction;

public class ContractCodeIsValid extends ValidationHandler {

  @Override
  public boolean validate(Transaction transaction, Blockchain blockchain) {
    SmartContractBase contract = blockchain.getSmartContract(transaction.getLinkedSmartContractAddress());

    List<Node> parties = contract.getParties();
    Integer amountBeforeExecution = 0;
    for (int i = 0; i < parties.size(); i++) {
      amountBeforeExecution += parties.get(i).getBalance();
      amountBeforeExecution += parties.get(i).getStakeValue();
    }

    SmartContractExecutor.invokeContractMethod(contract, transaction.getMethodName(), transaction.getParameters(),
        transaction.getArgs());

    parties = contract.getParties();
    Integer amountAfterExecution = 0;
    for (int i = 0; i < parties.size(); i++) {
      amountAfterExecution += parties.get(i).getBalance();
      amountAfterExecution += parties.get(i).getStakeValue();
    }

    if (amountBeforeExecution != amountAfterExecution)
      return false;

    if (this.nextHandler != null)
      return this.nextHandler.validate(transaction, blockchain);

    else
      return true;

  }

}
