public interface Transaction {
  String getId();

  String getSenderAddress();

  String getRecipientAddress();

  Integer setFee();

  String getTimeStamp();

  Object getEvents();

  SmartContract getLinkedSmartContract();

  void linkSmartContract(SmartContract contract);
}
