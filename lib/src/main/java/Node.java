public interface Node {
  void validateTransaction(Transaction transaction);

  void executeSmartContract(SmartContract contract);

  void joinBlockchain(EthereumBlockchain blockchain);
}
