public interface SmartContract {
  void setParties(Node[] parties);

  Node[] getParties();

  void setByteCode();

  String getCode();

  boolean validate();

  void deploy(Blockchain blockchain);

  void verifyConditions();

  void requestExecution(Blockchain blockchain);
}
