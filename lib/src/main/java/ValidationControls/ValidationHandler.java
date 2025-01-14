package ValidationControls;

import Blockchain.Blockchain;
import Transaction.Transaction;

public abstract class ValidationHandler {
  protected ValidationHandler nextHandler;

  public abstract boolean validate(Transaction transaction, Blockchain blockchain);

  public void setNextHandler(ValidationHandler nextHandler) {
    this.nextHandler = nextHandler;
  }

  public void removeNextHandler() {
    this.nextHandler = null;
  }
}
