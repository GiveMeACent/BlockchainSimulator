package Transaction;

import SmartContract.SmartContractBase;

public class SmartContractTransaction extends Transaction {
  private String contractAddress;
  private String methodName;
  private Class<?> parameters[];
  private Object[] args;

  public SmartContractTransaction(String callerAddress, String contractAddress, Integer fee,
      String methodName, Class<?> parameters[],
      Object[] args) {
    super(callerAddress, fee);
    this.methodName = methodName;
    this.contractAddress = contractAddress;
    this.parameters = parameters;
    this.args = args;
  }

  @Override
  public String getRecipientAddress() {
    return "";
  }

  @Override
  public Integer getAmountToTransfer() {
    return 0;
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
    return this.contractAddress;
  }

  @Override
  public String getMethodName() {
    return this.methodName;
  }

  @Override
  public Class<?>[] getParameters() {
    return this.parameters;
  }

  @Override
  public Object[] getArgs() {
    return this.args;
  }

  @Override
  public TransactionType getType() {
    return TransactionType.SMART_CONTRACT_EXECUTE;
  }

}
