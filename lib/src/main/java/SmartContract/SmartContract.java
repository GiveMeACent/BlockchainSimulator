package SmartContract;

import java.util.List;

import Node.Node;

public interface SmartContract {
  void setParties(List<Node> parties, List<String> partiesAddresses);

  List<Node> getParties();

  List<String> getPartiesAddresses();

  boolean verifyConditions();
}
