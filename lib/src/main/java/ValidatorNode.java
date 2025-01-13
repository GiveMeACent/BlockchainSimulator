public class ValidatorNode extends Node {

  private Integer stake;

  public ValidatorNode() {
    super();
    this.isValidator = true;
    this.stake = 0;
  }

  @Override
  public void reward(Integer amount) {
    this.balance += amount;
  }

  @Override
  public void penalize(Integer amount) {
    this.stake = this.stake - amount > 0 ? this.stake - amount : 0;
  }

  @Override
  public void executeSmartContract(SmartContract contract) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'executeSmartContract'");
  }

  @Override
  protected boolean validateTransaction(Transaction transaction) {
    if (transaction.getType() == TransactionType.MONETARY) {
      String callerAddress = transaction.getCallerAddress();
      String recipientAddress = transaction.getRecipientAddress();

      Node senderNode = this.blockchain.getNode(callerAddress);
      Node recipientNode = this.blockchain.getNode(recipientAddress);

      if (callerAddress == recipientAddress)
        return false;

      if (senderNode == null || recipientNode == null)
        return false;

      if (senderNode.balance < transaction.getAmountTransferred())
        return false;

      return true;
    }

    if (transaction.getType() == TransactionType.SMART_CONTRACT_DEPLOY) {
    }

    return false;
  }

  @Override
  public void putStake(Integer amount) {
    this.stake = amount;
  }

  @Override
  public Integer getStakeValue() {
    return this.stake;
  }

}
