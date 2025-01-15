package Transaction;

import SmartContract.SmartContractBase;

public class DeploymentTransaction extends Transaction {
  private SmartContractBase linkedSmartContract;

  public DeploymentTransaction(String id, String callerAddress, Integer fee, SmartContractBase contract) {
    super(callerAddress, fee);
    this.linkedSmartContract = contract;
    this.type = TransactionType.SMART_CONTRACT_DEPLOY;
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
  public String getLinkedSmartContractAddress() {
    return "";
  }

  @Override
  public String getMethodName() {
    return "";
  }

  @Override
  public TransactionType getType() {
    return TransactionType.SMART_CONTRACT_DEPLOY;
  }

}
