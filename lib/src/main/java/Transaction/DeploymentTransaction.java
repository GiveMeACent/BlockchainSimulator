package Transaction;

import SmartContract.SmartContractBase;

public class DeploymentTransaction extends Transaction {
  private SmartContractBase linkedSmartContract;
  private String contractAddress;

  public DeploymentTransaction(String callerAddress, Integer fee, SmartContractBase contract) {
    super(callerAddress, fee);
    this.linkedSmartContract = contract;
    this.contractAddress = "";
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
    return this.linkedSmartContract;
  }

  @Override
  public void setLinkedSmartContractAddress(String contractAddress) {
    this.contractAddress = contractAddress;
  }

  @Override
  public String getLinkedSmartContractAddress() {
    return this.contractAddress;
  }

  @Override
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
    return TransactionType.SMART_CONTRACT_DEPLOY;
  }

  @Override
  public Transaction clone() {
    Transaction trx = new DeploymentTransaction(this.callerAddress, this.fee, this.linkedSmartContract);
    trx.setLinkedSmartContractAddress(contractAddress);
    trx.setAddress(this.address);
    trx.timestamp = this.timestamp;
    return trx;
  }

}
