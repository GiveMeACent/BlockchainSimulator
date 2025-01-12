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
  public boolean registerTransaction(Transaction transaction) {
    if (!this.getConsesus(transaction))
      return false;

    if (blocks.isEmpty()) {
      blocks.add(new Block("GENESIS_HASH", 1));
    }

    Block lastBlock = blocks.get(blocks.size() - 1);

    transaction.apply(this);

    if (lastBlock != null && blocks.get(blocks.size() - 1).getCurrentSize() < Block.BLOCK_SIZE)
      blocks.get(blocks.size() - 1).addTransaction(transaction);

    else {
      lastBlock.calculateHash();
      Block newBlock = new Block(lastBlock.getHash(), blocks.size() + 1);
      newBlock.addTransaction(transaction);
      blocks.add(newBlock);
    }

    return true;
  }

  @Override
  public Transaction[] getTransactions(Integer blockNumber) {
    return this.blocks.get(blockNumber).getAllTransactions();
  }

  @Override
  public void executeSmartContract() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'executeSmartContract'");
  }

  private Node selectNextValidator() {
    Node[] participantNodes = nodesAddresses.values().toArray(new Node[0]);
    int maxStake = 0;
    int maxStakeNodeIndex = 0;

    for (int i = 0; i < participantNodes.length; i++) {
      if (participantNodes[i].getValidator() && participantNodes[i].getStakeValue() > maxStake) {
        maxStake = participantNodes[i].getStakeValue();
        maxStakeNodeIndex = i;
      }
    }

    return participantNodes[maxStakeNodeIndex];
  }

  private boolean getConsesus(Transaction transaction) {
    Node validatorNode = this.selectNextValidator();
    boolean resultFromSelectedNode = validatorNode.validateTransaction(transaction);

    Node[] participantNodes = nodesAddresses.values().toArray(new Node[0]);
    List<Boolean> resultsFromValidatorNodes = new ArrayList<Boolean>();
    Integer trueResults = 0;

    for (int i = 0; i < participantNodes.length; i++) {
      if (participantNodes[i].getValidator() && !participantNodes[i].equals(validatorNode)) {
        if (resultsFromValidatorNodes.add(participantNodes[i].validateTransaction(transaction))) {
          trueResults += 1;
        }
      }
    }

    if (trueResults >= participantNodes.length / 2 + 1) {
      if (resultFromSelectedNode)
        validatorNode.reward(transaction.getFee());
      else
        validatorNode.penalize(transaction.getFee());
      return true;
    } else {
      if (!resultFromSelectedNode)
        validatorNode.reward(transaction.getFee());
      else
        validatorNode.penalize(transaction.getFee());
      return false;
    }
  }

  private void delegateSmartContractExecution() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delegateSmartContractExecution'");
  }

}