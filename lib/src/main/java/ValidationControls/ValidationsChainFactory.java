package ValidationControls;

import Transaction.Transaction;
import Transaction.TransactionType;

public class ValidationsChainFactory {
  public ValidationHandler getValidationChain(Transaction transaction) {

    ValidationHandler handlerChain = null;

    switch (transaction.getType()) {
      case TransactionType.MONETARY:
        handlerChain = new CallerExists();
        handlerChain.setNextHandler(new RecipientExists());
        handlerChain.setNextHandler(new BalanceSuffices());
        break;

      case TransactionType.SMART_CONTRACT_DEPLOY:
        handlerChain = new CallerExists();
        handlerChain.setNextHandler(new ContractIsValid());
        handlerChain.setNextHandler(new BalanceSuffices());
        break;

      case TransactionType.SMART_CONTRACT_EXECUTE:
        handlerChain = new CallerExists();
        handlerChain.setNextHandler(new ContractExists());
        handlerChain.setNextHandler(new MethodExists());
        handlerChain.setNextHandler(new BalanceSuffices());
        break;
    }

    return handlerChain;
  }
}
