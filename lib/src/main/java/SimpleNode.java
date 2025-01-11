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
  protected boolean validateTransaction(Transaction transaction) {
    return false;
  }

  @Override
  public void executeSmartContract(SmartContract contract) {
    return;
  }

  @Override
  public Integer getStakeValue() {
    return 0;
  }

}
