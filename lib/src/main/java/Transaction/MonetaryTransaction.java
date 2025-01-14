package Transaction;

import SmartContract.SmartContractBase;

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
  public SmartContractBase getLinkedSmartContract() {
    return null;
  }

  @Override
  public String getLinkedSmartContractAddress() {
    return "";
  }

  public String getMethodName() {
    return "";
  }

  @Override
  public TransactionType getType() {
    return this.type;
  }

}
