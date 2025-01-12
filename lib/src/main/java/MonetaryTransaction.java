import java.time.LocalDate;

public class MonetaryTransaction implements Transaction {

  private String id;
  private String senderAddress;
  private String recipientAddress;
  private Integer amountTransferred;
  private Integer fee;
  private String timestamp;
  private TransactionType type;

  public MonetaryTransaction(String id, String senderAddress, String recipientAddress, Integer amountTransferred,
      Integer fee) {
    this.id = id;
    this.senderAddress = senderAddress;
    this.recipientAddress = recipientAddress;
    this.amountTransferred = amountTransferred;
    this.fee = fee;
    this.type = TransactionType.MONETARY;
  }

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public String getSenderAddress() {
    return this.senderAddress;
  }

  @Override
  public String getRecipientAddress() {
    return this.recipientAddress;
  }

  @Override
  public Integer getAmountTransferred() {
    return this.amountTransferred;
  }

  @Override
  public Integer getFee() {
    return this.fee;
  }

  @Override
  public String getTimeStamp() {
    return this.timestamp;
  }

  @Override
  public TransactionType getType() {
    return this.type;
  }

  @Override
  public void linkSmartContract(SmartContract contract) {
    return;
  }

  @Override
  public SmartContract getLinkedSmartContract() {
    return null;
  }

  @Override
  public void apply(Blockchain blockchain) {
    Node senderNode = blockchain.getNode(this.senderAddress);
    Node recipientNode = blockchain.getNode(this.recipientAddress);

    senderNode.setBalance(senderNode.getBalance() - this.amountTransferred - this.fee);
    recipientNode.setBalance(recipientNode.getBalance() + this.amountTransferred);
    this.timestamp = LocalDate.now().toString();
  }
}
