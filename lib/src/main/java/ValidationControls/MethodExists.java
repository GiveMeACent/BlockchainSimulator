package ValidationControls;

import java.lang.reflect.Method;

import Blockchain.Blockchain;
import SmartContract.SmartContractBase;
import Transaction.Transaction;

public class MethodExists extends ValidationHandler {

  @Override
  public boolean validate(Transaction transaction, Blockchain blockchain) {
    SmartContractBase contract = blockchain.getSmartContract(transaction.getLinkedSmartContractAddress());
    Class<? extends SmartContractBase> c = contract.getClass();
    Method m[] = c.getDeclaredMethods();

    boolean found = false;
    for (int i = 0; i < m.length; i++) {
      if (m[i].getName().equals(transaction.getMethodName())) {
        found = true;
        break;
      }
    }
    return found;
  }

}
