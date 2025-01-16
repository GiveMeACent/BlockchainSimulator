package ValidationControls;

import java.util.ArrayList;
import java.util.List;

import Blockchain.Blockchain;
import Blockchain.ProofOfStakeBlockchain;
import Node.Node;
import SmartContract.SmartContractBase;
import SmartContract.SmartContractExecutor;
import Transaction.Transaction;

public class ContractCodeIsValid extends ValidationHandler {

  @Override
  public boolean validate(Transaction transaction, Blockchain blockchain) {
    SmartContractBase contract = blockchain.getSmartContract(transaction.getLinkedSmartContractAddress());

    List<Node> parties = new ArrayList<Node>();
    List<String> partiesAddresses = contract.getPartiesAddresses();
    Node currentParty;
    Integer amountBeforeExecution = 0;
    for (int i = 0; i < partiesAddresses.size(); i++) {
      currentParty = ProofOfStakeBlockchain.getInstance().getNode(partiesAddresses.get(i));
      amountBeforeExecution += currentParty.getBalance();
      amountBeforeExecution += currentParty.getStakeValue();
      parties.add(currentParty);
    }

    contract.setParties(parties);

    SmartContractExecutor.invokeContractMethod(contract, transaction.getMethodName(), transaction.getParameters(),
        transaction.getArgs());

    parties = contract.getParties();
    Integer amountAfterExecution = 0;
    for (int i = 0; i < parties.size(); i++) {
      amountAfterExecution += parties.get(i).getBalance();
      amountAfterExecution += parties.get(i).getStakeValue();
    }

    if (!amountBeforeExecution.equals(amountAfterExecution))
      return false;

    if (this.nextHandler != null)
      return this.nextHandler.validate(transaction, blockchain);

    else
      return true;

  }

}
