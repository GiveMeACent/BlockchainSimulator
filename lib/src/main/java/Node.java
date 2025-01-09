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
  }

  public boolean getValidatorStatus() {
    return this.isValidator;
  }

  protected void setValidatorStatus(boolean status) {
    this.isValidator = status;
  }

  public void putStake(Integer amount) {
    this.stake = amount;
  }

  Integer getStakeValue() {
    return stake;
  }

  abstract void penalize(Integer amount);

  abstract void validateTransaction(Transaction transaction);

  abstract void executeSmartContract(SmartContract contract);

  abstract void sendResultToBlockchain(Block block);

}
