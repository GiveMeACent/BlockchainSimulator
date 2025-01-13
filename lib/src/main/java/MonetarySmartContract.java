public class MonetarySmartContract implements SmartContract {
  private Node[] parties;
  private String byteCode;

  @Override
  public void setParties(Node[] parties) {
    this.parties = parties;
  }

  @Override
  public Node[] getParties() {
    return this.parties;
  }

  @Override
  public void setByteCode(String byteCode) {
    this.byteCode = byteCode;
  }

  @Override
  public String getByteCode() {
    return this.byteCode;
  }

  @Override
  public boolean validate() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'validate'");
  }

  @Override
  public void verifyConditions() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'verifyConditions'");
  }

  @Override
  public void requestExecution(Blockchain blockchain) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'requestExecution'");
  }

}
