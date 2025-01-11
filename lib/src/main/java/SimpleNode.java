public class SimpleNode extends Node {

  public SimpleNode() {
    super();
    this.setValidator(false);
  }

  @Override
  public void putStake(Integer amount) {
    return;
  }

  @Override
  public void penalize(Integer amount) {
    return;
  }

  @Override
  protected void sendResultToBlockchain(Block block) {
    return;
  }

  @Override
  public void executeSmartContract(SmartContract contract) {
    return;
  }

}
