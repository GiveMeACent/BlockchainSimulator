package ValidationControls;

import Blockchain.Blockchain;
import Node.Node;
import Transaction.Transaction;

public class RecipientExists extends ValidationHandler {

  @Override
  public boolean validate(Transaction transaction, Blockchain blockchain) {
    String recipientAddress = transaction.getRecipientAddress();
    Node recipientNode = blockchain.getNode(recipientAddress);

    if (recipientNode == null)
      return false;

    if (this.nextHandler != null)
      this.nextHandler.validate(transaction, blockchain);

    return true;

  }

}
