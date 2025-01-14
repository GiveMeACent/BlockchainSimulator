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
        handlerChain.setNextHandler(new BalanceSuffices());
        handlerChain.setNextHandler(new ContractIsValid());
        break;

      case TransactionType.SMART_CONTRACT_EXECUTE:
        handlerChain = new CallerExists();
        handlerChain.setNextHandler(new BalanceSuffices());
        handlerChain.setNextHandler(new ContractExists());
        handlerChain.setNextHandler(new MethodExists());
        break;
    }

    return handlerChain;
  }
}
