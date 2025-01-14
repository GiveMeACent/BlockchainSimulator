package Blockchain;

import Node.Node;
import SmartContract.SmartContract;
import Transaction.Transaction;

public interface Blockchain {
  String registerNode(Node node);

  Node getNode(String nodeAddress);

  SmartContract getSmartContract(String smartContractAddress);

  boolean registerTransaction(Transaction transaction);

  Transaction[] getTransactions(Integer blockNumber);
}
