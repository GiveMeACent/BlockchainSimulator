package ValidationControls;

import Blockchain.Blockchain;
import SmartContract.SmartContractBase;
import Transaction.Transaction;

public class ContractSatisfiesConditions extends ValidationHandler {

  @Override
  public boolean validate(Transaction transaction, Blockchain blockchain) {
    SmartContractBase contract = blockchain.getSmartContract(transaction.getLinkedSmartContractAddress());
    if (!contract.verifyConditions())
      return false;

    if (this.nextHandler != null)
      return this.nextHandler.validate(transaction, blockchain);

    else
      return true;
  }

}
