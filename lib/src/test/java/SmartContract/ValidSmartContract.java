package SmartContract;

import Node.Node;

public class ValidSmartContract extends SmartContractBase {

  public ValidSmartContract() {
    super();
  }

  @Override
  public boolean verifyConditions() {

    return this.parties.size() == 2;
  }

  public void transferToB(Integer amount) {
    Node nodeA = parties.get(0);
    Node nodeB = parties.get(1);
    nodeA.setBalance(nodeA.getBalance() - amount);
    nodeB.setBalance(nodeB.getBalance() + amount);
    this.doSomething();
  }

  private void doSomething() {
  }

}
