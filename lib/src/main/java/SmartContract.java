public interface SmartContract {
  void setParties(Node[] parties);

  Node[] getParties();

  void setByteCode(String byteCode);

  String getByteCode();

  boolean validate();

  void verifyConditions();

  void requestExecution(Blockchain blockchain);
}
