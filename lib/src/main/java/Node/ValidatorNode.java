package Node;

import SmartContract.SmartContract;
import Transaction.Transaction;
import ValidationControls.ValidationHandler;
import ValidationControls.ValidationsChainFactory;

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
  public boolean validateTransaction(Transaction transaction) {
    ValidationsChainFactory validationsFactory = new ValidationsChainFactory();
    ValidationHandler handlerChain = validationsFactory.getValidationChain(transaction);
    return handlerChain.validate(transaction, this.blockchain);
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
