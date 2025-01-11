
public interface Transaction {
  String getId();

  String getSenderAddress();

  String getRecipientAddress();

  Integer getAmountTransferred();

  Integer getFee();

  String getTimeStamp();

  TransactionType getType();

  void linkSmartContract(SmartContract contract);

  SmartContract getLinkedSmartContract();

}
