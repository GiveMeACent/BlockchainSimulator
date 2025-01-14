package Node;

import SmartContract.SmartContractBase;
import Transaction.Transaction;

public class SimpleNode extends Node {

  public SimpleNode() {
    super();
    this.isValidator = false;
  }

  @Override
  public void putStake(Integer amount) {
    return;
  }

  @Override
  public void reward(Integer amount) {
    return;
  }

  @Override
  public void penalize(Integer amount) {
    return;
  }

  @Override
  public boolean validateTransaction(Transaction transaction) {
    return false;
  }

  @Override
  public void executeSmartContractMethod(SmartContractBase contract, String methodName, Class<?> parameters,
      Object... args) {
    return;
  }

  @Override
  public Integer getStakeValue() {
    return 0;
  }

}
