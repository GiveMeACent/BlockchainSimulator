import java.time.LocalDate;

public class MonetaryTransaction implements Transaction {

  private String id;
  private String senderAddress;
  private String recipientAddress;
  private Integer amountTransferred;
  private Integer fee;
  private LocalDate date;
  private TransactionType type;

  public MonetaryTransaction(String id, String senderAddress, String recipientAddress, Integer amountTransferred,
      Integer fee) {
    this.id = id;
    this.senderAddress = senderAddress;
    this.recipientAddress = recipientAddress;
    this.amountTransferred = amountTransferred;
    this.fee = fee;
    this.date = LocalDate.now();
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
    return this.date.toString();
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

}
