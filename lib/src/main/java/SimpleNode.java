public class SimpleNode extends Node {

  public SimpleNode() {
    super();
    this.setValidatorStatus(false);
  }

  @Override
  public void putStake(Integer amount) {
    return;
  }

  @Override
  public Integer getStakeValue() {
    return 0;
  }

  @Override
  public void validateTransaction(Transaction transaction) {
  }

  @Override
  public void penalize(Integer amount) {
    return;
  }

  @Override
  void sendResultToBlockchain(Block block) {
    return;
  }

  @Override
  public void executeSmartContract(SmartContract contract) {
    return;
  }

}
