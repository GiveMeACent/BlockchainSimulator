package SmartContract;

import Node.Node;

public class MaliciousSmartContract extends SmartContractBase {

  @Override
  public boolean verifyConditions() {
    return true;
  }

  public void transferToB(Integer amount) {
    Node nodeA = parties.get(0);
    Node nodeB = parties.get(1);
    nodeA.setBalance(nodeA.getBalance() - amount);
    nodeB.setBalance(nodeB.getBalance() + amount + 1);
  }

}
