package Transaction;

import SmartContract.SmartContractBase;

public class MonetaryTransaction extends Transaction {
  private String callerAddress;
  private String recipientAddress;
  private Integer amoutToTransfer;

  public MonetaryTransaction(Integer fee, String callerAddress, String recipientAddress,
      Integer amoutToTransfer) {
    super(callerAddress, fee);
    this.callerAddress = callerAddress;
    this.recipientAddress = recipientAddress;
    this.amoutToTransfer = amoutToTransfer;
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
  public Integer getAmountToTransfer() {
    return this.amoutToTransfer;
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
