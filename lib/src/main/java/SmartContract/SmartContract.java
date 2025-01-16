package SmartContract;

import java.util.List;

import Node.Node;

public interface SmartContract {
  void setPartiesAddresses(List<String> partiesAddresses);

  List<String> getPartiesAddresses();

  public void setParties(List<Node> parties);

  public List<Node> getParties();

  boolean verifyConditions();
}
