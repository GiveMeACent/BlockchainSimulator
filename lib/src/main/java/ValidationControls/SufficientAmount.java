package ValidationControls;

import Blockchain.Blockchain;
import Node.Node;
import Transaction.Transaction;

public class SufficientAmount extends ValidationHandler {

  @Override
  public boolean validate(Transaction transaction, Blockchain blockchain) {
    String callerAddress = transaction.getCallerAddress();
    Node senderNode = blockchain.getNode(callerAddress);
    if (senderNode.getBalance() < transaction.getAmountTransferred())
      return false;

    if (this.nextHandler != null)
      this.nextHandler.validate(transaction, blockchain);

    return true;
  }

}
