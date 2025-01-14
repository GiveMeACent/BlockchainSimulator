package ValidationControls;

import Blockchain.Blockchain;
import SmartContract.SmartContractBase;
import Transaction.Transaction;

public class ContractIsValid extends ValidationHandler {

  @Override
  public boolean validate(Transaction transaction, Blockchain blockchain) {
    SmartContractBase contract = transaction.getLinkedSmartContract();
    if (contract == null)
      return false;

    if (!SmartContractBase.class.isAssignableFrom(contract.getClass()))
      return false;

    if (this.nextHandler != null)
      return this.nextHandler.validate(transaction, blockchain);

    else
      return true;

  }

}