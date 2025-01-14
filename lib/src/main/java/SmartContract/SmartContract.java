package SmartContract;

import Node.Node;

public interface SmartContract {
  void setParties(Node[] parties);

  Node[] getParties();

  void verifyConditions();
}
