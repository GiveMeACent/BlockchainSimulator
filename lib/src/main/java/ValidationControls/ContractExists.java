package ValidationControls;

import Blockchain.Blockchain;
import Transaction.Transaction;

public class ContractExists extends ValidationHandler {

  @Override
  public boolean validate(Transaction transaction, Blockchain blockchain) {
    if (blockchain.getSmartContract(transaction.getLinkedSmartContractAddress()) == null)
      return false;

    if (this.nextHandler != null)
      return this.nextHandler.validate(transaction, blockchain);

    else
      return true;
  }

}
