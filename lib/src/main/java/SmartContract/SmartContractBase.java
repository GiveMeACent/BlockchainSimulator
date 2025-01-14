package SmartContract;

import Node.Node;

public abstract class SmartContractBase implements SmartContract {
  private Node[] parties;

  @Override
  public void setParties(Node[] parties) {
    this.parties = parties;
  }

  @Override
  public Node[] getParties() {
    return this.parties;
  }

  @Override
  public abstract void verifyConditions();

}
