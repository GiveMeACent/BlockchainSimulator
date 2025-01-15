package SmartContract;

import java.util.ArrayList;
import java.util.List;

import Node.Node;

public abstract class SmartContractBase implements SmartContract {
  protected List<Node> parties;
  protected List<String> partiesAddresses;

  public SmartContractBase() {
    parties = new ArrayList<Node>();
    partiesAddresses = new ArrayList<String>();
  }

  @Override
  public void setParties(List<Node> parties, List<String> partiesAddresses) {
    for (int i = 0; i < parties.size(); i++) {
      this.parties.add(parties.get(i).clone());
    }
    this.partiesAddresses = partiesAddresses;
  }

  @Override
  public List<Node> getParties() {
    return this.parties;
  }

  @Override
  public List<String> getPartiesAddresses() {
    return this.partiesAddresses;
  }

  @Override
  public abstract boolean verifyConditions();
}
