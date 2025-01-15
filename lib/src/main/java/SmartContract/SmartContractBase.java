package SmartContract;

import java.util.ArrayList;
import java.util.List;

import Node.Node;

public abstract class SmartContractBase implements SmartContract {
  protected List<Node> parties;

  public SmartContractBase() {
    parties = new ArrayList<Node>();
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
