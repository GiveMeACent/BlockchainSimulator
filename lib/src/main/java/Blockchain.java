public interface Blockchain {
  String registerNode(Node node);

  Node getNode(String nodeAddress);

  String registerSmartContract(SmartContract smartContract);

  SmartContract getSmartContract(String smartContractAddress);

  boolean registerTransaction(Transaction transaction);

  Transaction getTransaction(String transactionAddress);

  void executeSmartContract();

  void selectNextValidator();

  void getConsensus();
}
