package Transaction;

import SmartContract.SmartContractBase;

public class MonetaryTransaction extends Transaction {
  private String callerAddress;
  private String recipientAddress;
  private Integer amountToTransfer;

  public MonetaryTransaction(Integer fee, String callerAddress, String recipientAddress,
      Integer amountToTransfer) {
    super(callerAddress, fee);
    this.callerAddress = callerAddress;
    this.recipientAddress = recipientAddress;
    this.amountToTransfer = amountToTransfer;
  }

  @Override
  public String getRecipientAddress() {
    return this.recipientAddress;
  }

  @Override
  public Integer getAmountToTransfer() {
    return this.amountToTransfer;
  }

  @Override
  public SmartContractBase getLinkedSmartContract() {
    return null;
  }

  @Override
  public void setLinkedSmartContractAddress(String contractAddress) {
    return;
  }

  @Override
  public String getLinkedSmartContractAddress() {
    return "";
  }

  public String getMethodName() {
    return "";
  }

  @Override
  public Class<?>[] getParameters() {
    return null;
  }

  @Override
  public Object[] getArgs() {
    return null;
  }

  @Override
  public TransactionType getType() {
    return TransactionType.MONETARY;
  }

  @Override
  public Transaction clone() {
    Transaction trx = new MonetaryTransaction(this.fee, this.callerAddress, this.recipientAddress,
        this.amountToTransfer);
    trx.setAddress(this.address);
    trx.timestamp = this.timestamp;
    return trx;
  }

}
