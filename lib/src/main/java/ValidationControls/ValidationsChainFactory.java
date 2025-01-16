package ValidationControls;

import Transaction.Transaction;
import Transaction.TransactionType;

public class ValidationsChainFactory {
  public ValidationHandler getValidationChain(Transaction transaction) {

    ValidationHandler handlerChain = null;

    switch (transaction.getType()) {
      case TransactionType.MONETARY:
        handlerChain = new CallerExists();
        handlerChain.addHandler(new RecipientExists());
        handlerChain.addHandler(new BalanceSuffices());
        break;

      case TransactionType.SMART_CONTRACT_DEPLOY:
        handlerChain = new CallerExists();
        handlerChain.addHandler(new ContractClassIsValid());
        handlerChain.addHandler(new BalanceSuffices());
        break;

      case TransactionType.SMART_CONTRACT_EXECUTE:
        handlerChain = new CallerExists();
        handlerChain.addHandler(new ContractExists());
        handlerChain.addHandler(new MethodExists());
        handlerChain.addHandler(new ContractCodeIsValid());
        handlerChain.addHandler(new ContractSatisfiesConditions());
        handlerChain.addHandler(new BalanceSuffices());
        break;

      default:
        break;
    }

    return handlerChain;
  }
}
