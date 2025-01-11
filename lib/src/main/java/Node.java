public abstract class Node {
  protected Blockchain blockchain;
  protected Integer stake;
  protected Integer balance;
  protected Boolean isValidator;

  public Node() {
    this.stake = 0;
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

  public void putStake(Integer amount) {
    this.stake = amount;
  }

  Integer getStakeValue() {
    return this.stake;
  }

  abstract public void penalize(Integer amount);

  abstract public void executeSmartContract(SmartContract contract);

  abstract protected void sendResultToBlockchain(Block block);

}
