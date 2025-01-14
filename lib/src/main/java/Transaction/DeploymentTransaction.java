package Transaction;

import SmartContract.SmartContractBase;

public class DeploymentTransaction extends Transaction {
  private SmartContractBase linkedSmartContract;

  public DeploymentTransaction(String id, String callerAddress, Integer fee, SmartContractBase contract) {
    super(id, callerAddress, fee);
    this.linkedSmartContract = contract;
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
