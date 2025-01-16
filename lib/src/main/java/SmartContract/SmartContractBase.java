package SmartContract;

import java.util.ArrayList;
import java.util.List;

import Node.Node;

public abstract class SmartContractBase implements SmartContract {
  protected List<String> partiesAddresses;
  protected List<Node> parties;

  public SmartContractBase() {
    partiesAddresses = new ArrayList<String>();
  }

  @Override
  public void setPartiesAddresses(List<String> partiesAddresses) {
    this.partiesAddresses = partiesAddresses;
  }

  @Override
  public List<String> getPartiesAddresses() {
    return this.partiesAddresses;
  }

  @Override
  public void setParties(List<Node> parties) {
    this.parties = parties;
  }

  @Override
  public List<Node> getParties() {
    return this.parties;
  }

  @Override
  public abstract boolean verifyConditions();
}
