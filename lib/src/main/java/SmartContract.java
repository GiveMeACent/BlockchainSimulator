public interface SmartContract {
  void setParties(Node party);

  Node[] getParties();

  void setByteCode();

  String getCode();

  boolean validate();

  void deploy();

  void verifyConditions();

  void requestExecution(Blockchain blockchain);
}
