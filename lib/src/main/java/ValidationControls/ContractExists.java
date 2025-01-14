package ValidationControls;

import Blockchain.Blockchain;
import Transaction.Transaction;

public class ContractExists extends ValidationHandler {

  @Override
  public boolean validate(Transaction transaction, Blockchain blockchain) {
    return blockchain.getSmartContract(transaction.getLinkedSmartContractAddress()) == null;
  }

}
