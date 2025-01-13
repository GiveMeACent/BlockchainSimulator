import java.time.LocalDate;

public class MonetaryTransaction extends Transaction {
  private String callerAddress;
  private String recipientAddress;
  private Integer amountTransferred;

  public MonetaryTransaction(String id, Integer fee, String callerAddress, String recipientAddress,
      Integer amountTransferred) {
    super(id, callerAddress, fee);
    this.callerAddress = callerAddress;
    this.recipientAddress = recipientAddress;
    this.amountTransferred = amountTransferred;
    this.type = TransactionType.MONETARY;
  }

  @Override
  public String getCallerAddress() {
    return this.callerAddress;
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
  public SmartContract getLinkedSmartContract() {
    return null;
  }

  @Override
  public TransactionType getType() {
    return this.type;
  }

  @Override
  public void apply(Blockchain blockchain) {
    Node senderNode = blockchain.getNode(this.callerAddress);
    Node recipientNode = blockchain.getNode(this.recipientAddress);

    senderNode.setBalance(senderNode.getBalance() - this.amountTransferred - this.fee);
    recipientNode.setBalance(recipientNode.getBalance() + this.amountTransferred);
    this.timestamp = LocalDate.now().toString();
  }
}
