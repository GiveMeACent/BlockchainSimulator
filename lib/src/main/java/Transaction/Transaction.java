package Transaction;

import java.time.LocalDate;
import SmartContract.SmartContractBase;

public abstract class Transaction {

  private String id;
  protected Integer fee;
  protected String timestamp;
  protected TransactionType type;
  protected String callerAddress;

  public Transaction(String id, String callerAddress, Integer fee) {
    this.id = id;
    this.callerAddress = callerAddress;
    this.fee = fee;
  }

  public String getId() {
    return this.id;
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

  abstract public Integer getAmountTransferred();

  abstract public SmartContractBase getLinkedSmartContract();

  abstract public String getLinkedSmartContractAddress();

  abstract public String getMethodName();

  abstract public TransactionType getType();

}
