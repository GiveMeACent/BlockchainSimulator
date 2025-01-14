package Node;

import Blockchain.Blockchain;
import SmartContract.SmartContract;
import Transaction.Transaction;

public abstract class Node {
  protected Blockchain blockchain;
  protected Integer balance;
  protected Boolean isValidator;

  public Node() {
  }

  public void joinBlockchain(Blockchain blockchain) {
    this.blockchain = blockchain;
    blockchain.registerNode(this);
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

  abstract public void executeSmartContract(SmartContract contract);

  abstract public boolean validateTransaction(Transaction transaction);

}
