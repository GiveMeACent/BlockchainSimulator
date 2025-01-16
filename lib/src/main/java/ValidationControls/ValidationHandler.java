package ValidationControls;

import Blockchain.Blockchain;
import Transaction.Transaction;

public abstract class ValidationHandler {
  protected ValidationHandler nextHandler = null;

  public abstract boolean validate(Transaction transaction, Blockchain blockchain);

  public void addHandler(ValidationHandler nextHandler) {
    if (this.nextHandler == null)
      this.nextHandler = nextHandler;
    else
      this.nextHandler.addHandler(nextHandler);
  }

  public void removeNextHandler() {
    this.nextHandler = null;
  }
}
