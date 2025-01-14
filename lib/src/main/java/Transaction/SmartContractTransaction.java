package Transaction;

import SmartContract.SmartContractBase;

public class SmartContractTransaction extends Transaction {
  private String contractAddress;
  private String methodName;

  public SmartContractTransaction(String id, String callerAddress, Integer fee, String contractAddress,
      String methodName) {
    super(id, callerAddress, fee);
    this.contractAddress = contractAddress;
    this.methodName = methodName;
    this.type = TransactionType.SMART_CONTRACT_EXECUTE;
  }

  @Override
  public String getRecipientAddress() {
    return "";
  }

  @Override
  public Integer getAmountTransferred() {
    return 0;
  }

  @Override
  public SmartContractBase getLinkedSmartContract() {
    return null;
  }

  @Override
  public String getLinkedSmartContractAddress() {
    return this.contractAddress;
  }

  @Override
  public String getMethodName() {
    return this.methodName;
  }

  @Override
  public TransactionType getType() {
    return TransactionType.SMART_CONTRACT_EXECUTE;
  }

}
