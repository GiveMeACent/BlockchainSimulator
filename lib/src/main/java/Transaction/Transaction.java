package Transaction;

import java.time.LocalDate;
import SmartContract.SmartContractBase;

public abstract class Transaction {

  protected String address;
  protected Integer fee;
  protected String timestamp;
  protected TransactionType type;
  protected String callerAddress;

  public Transaction(String callerAddress, Integer fee) {
    this.callerAddress = callerAddress;
    this.fee = fee;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getAddress() {
    return this.address;
  }

  public Integer getFee() {
    return this.fee;
  }

  public void setTimeStamp() {
    this.timestamp = LocalDate.now().toString();
  }

  public String getTimeStamp() {
    return this.timestamp;
  }

  public String getCallerAddress() {
    return this.callerAddress;
  }

  abstract public String getRecipientAddress();

  abstract public Integer getAmountToTransfer();

  abstract public SmartContractBase getLinkedSmartContract();

  abstract public void setLinkedSmartContractAddress(String contractAddress);

  abstract public String getLinkedSmartContractAddress();

  abstract public String getMethodName();

  abstract public Class<?>[] getParameters();

  abstract public Object[] getArgs();

  abstract public TransactionType getType();

  abstract public Transaction clone();

}
