package Node;

import Blockchain.Blockchain;
import SmartContract.SmartContractBase;
import Transaction.Transaction;

public abstract class Node {
  protected Blockchain blockchain;
  protected Integer balance;
  protected Boolean isValidator;

  public Node() {
  }

  public String joinBlockchain(Blockchain blockchain) {
    this.blockchain = blockchain;
    return blockchain.registerNode(this);
  }

  public void setBalance(Integer amount) {
    this.balance = amount;
  }

  public Integer getBalance() {
    return this.balance;
  }

  public boolean getValidator() {
    return this.isValidator;
  }

  protected void setValidator(boolean status) {
    this.isValidator = status;
  }

  abstract public void putStake(Integer amount);

  abstract public Integer getStakeValue();

  abstract public void reward(Integer amount);

  abstract public void penalize(Integer amount);

  abstract public void executeSmartContractMethod(SmartContractBase contract, String methodName, Class<?> parameters,
      Object... args);

  abstract public boolean validateTransaction(Transaction transaction);

}
