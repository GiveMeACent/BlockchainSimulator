public interface Blockchain {
  String registerNode(Node node);

  Node getNode(String nodeAddress);

  SmartContract getSmartContract(String smartContractAddress);

  boolean registerTransaction(Transaction transaction);

  Transaction[] getTransactions(Integer blockNumber);
}
