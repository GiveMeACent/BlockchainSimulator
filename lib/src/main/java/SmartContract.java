public interface SmartContract {
  void addParty(Node party);

  Node[] getParties();

  void setCode();

  String getCode();
}
