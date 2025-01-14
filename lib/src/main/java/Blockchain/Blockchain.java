package Blockchain;

import Node.Node;
import SmartContract.SmartContractBase;
import Transaction.Transaction;

public interface Blockchain {
  String registerNode(Node node);

  Node getNode(String nodeAddress);

  SmartContractBase getSmartContract(String smartContractAddress);

  boolean requestTransactionRegistration(Transaction transaction);

  Transaction[] getTransactions(Integer blockNumber);
}
