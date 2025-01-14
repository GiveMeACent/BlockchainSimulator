package Blockchain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Block.Block;
import Node.Node;
import SmartContract.SmartContractBase;
import Transaction.Transaction;
import Transaction.TransactionType;
import Utils.HashUtils;

public class ProofOfStakeBlockchain implements Blockchain {
  private static Blockchain instance = null;
  private HashMap<String, Node> nodesAddresses;
  private HashMap<String, SmartContractBase> contractsAddresses;
  private List<Block> blocks;

  private ProofOfStakeBlockchain() {
    nodesAddresses = new HashMap<String, Node>();
    contractsAddresses = new HashMap<String, SmartContractBase>();
    blocks = new ArrayList<Block>();
  }

  public static Blockchain getInstance() {
    if (instance == null)
      instance = new ProofOfStakeBlockchain();
    return instance;
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
  public SmartContractBase getSmartContract(String smartContractAddress) {
    return contractsAddresses.get(smartContractAddress);
  }

  @Override
  public boolean requestTransactionRegistration(Transaction transaction) {
    if (!this.getConsesus(transaction))
      return false;

    if (blocks.isEmpty()) {
      blocks.add(new Block("GENESIS_HASH", 1));
    }

    Block lastBlock = blocks.get(blocks.size() - 1);

    this.applyTransaction(transaction);

    if (blocks.get(blocks.size() - 1).getCurrentSize() < Block.BLOCK_SIZE)
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
    Block block = this.blocks.get(blockNumber);
    if (block != null)
      return this.blocks.get(blockNumber).getAllTransactions();
    return null;
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

  private void applyTransaction(Transaction transaction) {
    switch (transaction.getType()) {
      case TransactionType.MONETARY:
        Node senderNode = nodesAddresses.get(transaction.getCallerAddress());
        Node recipientNode = nodesAddresses.get(transaction.getRecipientAddress());
        senderNode.setBalance(senderNode.getBalance() - transaction.getAmountTransferred() - transaction.getFee());
        recipientNode.setBalance(recipientNode.getBalance() + transaction.getAmountTransferred());
        transaction.setTimeStamp();
        break;

      case TransactionType.SMART_CONTRACT_DEPLOY:
        String contractAddress = HashUtils.generateRandomAddress();
        contractsAddresses.put(contractAddress, transaction.getLinkedSmartContract());

      case TransactionType.SMART_CONTRACT_EXECUTE:

        break;
      default:
        break;
    }
  }
}