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
        handlerChain.setNextHandler(new SufficientAmount());
        break;

      case TransactionType.SMART_CONTRACT_DEPLOY:
        break;

      case TransactionType.SMART_CONTRACT_EXECUTE:
        break;
    }

    return handlerChain;
  }
}
