import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProofOfStakeBlockchain implements Blockchain {
  private HashMap<String, Node> nodesAddresses;
  private HashMap<String, SmartContract> contractsAddresses;
  private List<Block> blocks;

  public ProofOfStakeBlockchain() {
    nodesAddresses = new HashMap<String, Node>();
    contractsAddresses = new HashMap<String, SmartContract>();
    blocks = new ArrayList<Block>();
  }

  @Override
  public String registerNode(Node node) {
    String nodeAddress = HashUtils.generateRandomAddress();
    nodesAddresses.put(nodeAddress, node);
    return nodeAddress;
  }

  @Override
  public Node getNode(String nodeAddress) {
    return nodesAddresses.get(nodeAddress);
  }

  @Override
  public String registerSmartContract(SmartContract smartContract) {
    String smartContractAddress = HashUtils.generateRandomAddress();
    contractsAddresses.put(smartContractAddress, smartContract);
    return smartContractAddress;
  }

  @Override
  public SmartContract getSmartContract(String smartContractAddress) {
    return contractsAddresses.get(smartContractAddress);
  }

  @Override
  public void invokeSmartContract(String smartContractAddress) {
  }

  @Override
  public void getConsensus() {
  }

  @Override
  public void proposeBlock(Block block) {
  }

  @Override
  public boolean addTransaction(Transaction transaction) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addTransaction'");
  }

  @Override
  public Transaction getTransaction(String transactionAddress) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getTransaction'");
  }

}