package SmartContract;

import java.util.List;

import Node.Node;

public interface SmartContract {
  void setParties(List<Node> parties);

  List<Node> getParties();

  boolean verifyConditions();
}
