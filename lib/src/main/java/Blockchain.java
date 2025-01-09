public interface Blockchain {
  String registerNode(Node node);

  Node getNode(String nodeAddress);

  String registerSmartContract(SmartContract smartContract);

  SmartContract getSmartContract(String smartContractAddress);

  void invokeSmartContract(String smartContractAddress);

  void getConsensus();

  void proposeBlock(Block block);

  boolean addTransaction(Transaction transaction);

  Transaction getTransaction(String transactionAddress);

}
