package SmartContract;

public class ExampleSmartContract extends SmartContractBase {
  public ExampleSmartContract() {
    super();
  }

  @Override
  public boolean verifyConditions() {
    return this.parties.size() == 2;
  }

}
