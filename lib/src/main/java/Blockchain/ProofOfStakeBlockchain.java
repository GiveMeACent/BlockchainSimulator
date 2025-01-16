package Blockchain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Block.Block;
import Node.Node;
import SmartContract.SmartContractBase;
import SmartContract.SmartContractExecutor;
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
    Node node = nodesAddresses.get(nodeAddress);
    if (node != null)
      return node.clone();
    else
      return null;
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
  public List<Transaction> getTransactions(Integer blockNumber) {
    Block block = this.blocks.get(blockNumber);
    if (block != null)
      return this.blocks.get(blockNumber).getAllTransactions();
    return null;
  }

  private Integer calculateTotalTransactionFee(Transaction transaction) {
    Integer totalFee = transaction.getFee();
    if (transaction.getType() == TransactionType.SMART_CONTRACT_DEPLOY)
      totalFee += SmartContractExecutor.evaluateContractCost(transaction.getLinkedSmartContract());
    if (transaction.getType() == TransactionType.SMART_CONTRACT_EXECUTE)
      totalFee += SmartContractExecutor.evaluateMethodCost(transaction.getLinkedSmartContract(),
          transaction.getMethodName(), transaction.getParameters());
    return totalFee;
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
    Integer trueResults = resultFromSelectedNode ? 1 : 0;
    Integer validatorNodes = 0;
    boolean currentValidatorNodeResult;

    for (int i = 0; i < participantNodes.length; i++) {
      if (participantNodes[i].getValidator() && !participantNodes[i].equals(validatorNode)) {
        validatorNodes += 1;
        currentValidatorNodeResult = participantNodes[i].validateTransaction(transaction);
        resultsFromValidatorNodes.add(currentValidatorNodeResult);
        if (currentValidatorNodeResult)
          trueResults += 1;
      }
    }

    if (trueResults >= validatorNodes / 2 + 1) {
      if (resultFromSelectedNode)
        validatorNode.reward(this.calculateTotalTransactionFee(transaction));
      else
        validatorNode.penalize(this.calculateTotalTransactionFee(transaction));
      return true;
    } else {
      if (!resultFromSelectedNode)
        validatorNode.reward(this.calculateTotalTransactionFee(transaction));
      else
        validatorNode.penalize(this.calculateTotalTransactionFee(transaction));
      return false;
    }
  }

  private void applyTransaction(Transaction transaction) {
    Node senderNode = nodesAddresses.get(transaction.getCallerAddress());
    senderNode.setBalance(
        senderNode.getBalance() - transaction.getAmountToTransfer() - this.calculateTotalTransactionFee(transaction));

    switch (transaction.getType()) {
      case TransactionType.MONETARY:
        Node recipientNode = nodesAddresses.get(transaction.getRecipientAddress());
        recipientNode.setBalance(recipientNode.getBalance() + transaction.getAmountToTransfer());
        transaction.setTimeStamp();
        break;

      case TransactionType.SMART_CONTRACT_DEPLOY:
        String contractAddress = HashUtils.generateRandomAddress();
        transaction.setLinkedSmartContractAddress(contractAddress);
        contractsAddresses.put(contractAddress, transaction.getLinkedSmartContract());
        break;

      case TransactionType.SMART_CONTRACT_EXECUTE:
        SmartContractBase contract = contractsAddresses.get(transaction.getLinkedSmartContractAddress());
        List<Node> parties = contract.getParties();
        List<String> partiesAddresses = contract.getPartiesAddresses();
        Node currentNode;
        for (int i = 0; i < parties.size(); i++) {
          currentNode = nodesAddresses.get(partiesAddresses.get(i));
          currentNode.setBalance(parties.get(i).getBalance());
          currentNode.putStake(parties.get(i).getStakeValue());
        }

        break;
      default:
        break;
    }
  }
}