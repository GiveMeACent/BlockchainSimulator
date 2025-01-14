package Transaction;

import Blockchain.Blockchain;
import SmartContract.SmartContract;

public class SmartContractTransaction extends Transaction {
  private SmartContract linkedSmartContract;

  public SmartContractTransaction(String id, String callerAddress, Integer fee, SmartContract contract) {
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
  public SmartContract getLinkedSmartContract() {
    return this.linkedSmartContract;
  }

  @Override
  public TransactionType getType() {
    return TransactionType.SMART_CONTRACT_EXECUTE;
  }

  @Override
  public void apply(Blockchain blockchain) {
    return;
  }
}
