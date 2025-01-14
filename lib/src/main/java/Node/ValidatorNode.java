package Node;

import SmartContract.SmartContractBase;
import SmartContract.SmartContractExecutor;
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
    this.stake += amount;
  }

  @Override
  public void penalize(Integer amount) {
    this.stake = this.stake - amount > 0 ? this.stake - amount : 0;
  }

  @Override
  public void executeSmartContractMethod(SmartContractBase contract, String methodName, Class<?> parameters,
      Object... args) {
    try {
      SmartContractExecutor.invokeContractMethod(contract, methodName, parameters, args);
    } catch (Exception e) {
      return;
    }
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
