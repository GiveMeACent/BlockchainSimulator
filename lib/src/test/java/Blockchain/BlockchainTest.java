package Blockchain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Node.Node;
import Node.ValidatorNode;
import SmartContract.ValidSmartContract;
import SmartContract.MaliciousSmartContract;
import SmartContract.SmartContractBase;
import Transaction.DeploymentTransaction;
import Transaction.MonetaryTransaction;
import Transaction.SmartContractTransaction;
import Transaction.Transaction;
import Node.SimpleNode;

public class BlockchainTest {
  @Test
  void testRegisterNode() {
    Blockchain blockchain = ProofOfStakeBlockchain.getInstance();
    Node nodeOne = new SimpleNode();

    String nodeOneAddress = nodeOne.joinBlockchain(blockchain);
    assertEquals(nodeOne, blockchain.getNode(nodeOneAddress));
  }

  @Test
  void functionalityTest() {
    Blockchain blockchain = ProofOfStakeBlockchain.getInstance();

    Node firstNode = new SimpleNode();
    String firstNodeAddress = firstNode.joinBlockchain(blockchain);

    Node secondNode = new SimpleNode();
    String secondNodeAddress = secondNode.joinBlockchain(blockchain);

    Node firstValidatorNode = new ValidatorNode();
    firstValidatorNode.joinBlockchain(blockchain);

    Node secondValidatorNode = new ValidatorNode();
    secondValidatorNode.joinBlockchain(blockchain);

    Node thirdValidatorNode = new ValidatorNode();
    thirdValidatorNode.joinBlockchain(blockchain);

    Node fourthValidatorNode = new ValidatorNode();
    fourthValidatorNode.joinBlockchain(blockchain);

    firstNode.setBalance(10);
    secondNode.setBalance(20);
    firstValidatorNode.putStake(30);
    secondValidatorNode.putStake(40);
    thirdValidatorNode.putStake(50);
    fourthValidatorNode.putStake(60);

    Transaction firstTransaction = new MonetaryTransaction(5, firstNodeAddress, secondNodeAddress, 5);

    assertEquals(true, blockchain.requestTransactionRegistration(firstTransaction));
    assertEquals(0, firstNode.getBalance());
    assertEquals(25, secondNode.getBalance());
    assertEquals(65, fourthValidatorNode.getStakeValue());

    firstNode.setBalance(10);
    secondNode.setBalance(20);
    firstValidatorNode.putStake(70);

    assertEquals(true, blockchain.requestTransactionRegistration(firstTransaction));
    assertEquals(75, firstValidatorNode.getStakeValue());

    Transaction secondTransaction = new MonetaryTransaction(5, firstNodeAddress, secondNodeAddress, 30);

    assertEquals(false, blockchain.requestTransactionRegistration(secondTransaction));

    Transaction thirdTransaction = new MonetaryTransaction(5, firstNodeAddress, "FAKE_ADDRESS", 30);

    assertEquals(false, blockchain.requestTransactionRegistration(thirdTransaction));

    Transaction fourthTransaction = new MonetaryTransaction(5, "FAKE_ADDRESS", secondNodeAddress, 30);

    assertEquals(false, blockchain.requestTransactionRegistration(fourthTransaction));

    List<Transaction> firstBlockTransactions = blockchain.getTransactions(0);
    List<Transaction> secondBlockTransactions;
    List<Transaction> expectedFirstBlockTransaction = List.of(firstTransaction, firstTransaction);
    List<Transaction> expectedSecondBlockTransaction = List.of(firstTransaction);

    assertEquals(expectedFirstBlockTransaction, firstBlockTransactions);

    firstNode.setBalance(1000);
    secondNode.setBalance(20);
    expectedFirstBlockTransaction = new ArrayList<Transaction>();
    expectedFirstBlockTransaction.add(firstTransaction);
    expectedFirstBlockTransaction.add(firstTransaction);

    for (int i = 0; i < 28; i++) {
      expectedFirstBlockTransaction.add(firstTransaction);
      assertEquals(true, blockchain.requestTransactionRegistration(firstTransaction));
    }

    firstBlockTransactions = blockchain.getTransactions(0);

    assertEquals(expectedFirstBlockTransaction, firstBlockTransactions);

    assertEquals(true, blockchain.requestTransactionRegistration(firstTransaction));

    secondBlockTransactions = blockchain.getTransactions(1);
    expectedSecondBlockTransaction = List.of(firstTransaction);

    assertEquals(expectedSecondBlockTransaction, secondBlockTransactions);

    SmartContractBase validContract = new ValidSmartContract();
    validContract.setPartiesAddresses(List.of(firstNodeAddress, secondNodeAddress));

    firstNode.setBalance(15);
    secondNode.setBalance(20);

    Transaction fifthTransaction = new DeploymentTransaction(firstNodeAddress, 6, validContract);
    assertEquals(false, blockchain.requestTransactionRegistration(fifthTransaction));

    firstNode.setBalance(21);
    assertEquals(true, blockchain.requestTransactionRegistration(fifthTransaction));
    secondBlockTransactions = blockchain.getTransactions(1);
    assertEquals(fifthTransaction, secondBlockTransactions.getLast());
    assertEquals(fifthTransaction.getLinkedSmartContract(), validContract);
    assertEquals(0, firstNode.getBalance());

    firstNode.setBalance(250);
    secondNode.setBalance(20);
    SmartContractBase maliciousContract = new MaliciousSmartContract();
    Transaction sixthTransaction = new DeploymentTransaction(firstNodeAddress, 5, maliciousContract);
    assertEquals(true, blockchain.requestTransactionRegistration(sixthTransaction));

    firstNode.setBalance(20);
    secondNode.setBalance(30);

    Class<?>[] paramTypes = new Class[1];
    paramTypes[0] = Integer.class;
    Object[] args = { 10 };

    Transaction seventhTransaction = new SmartContractTransaction(firstNodeAddress,
        fifthTransaction.getLinkedSmartContractAddress(), 5, "transferToB",
        paramTypes, args);

    assertEquals(true,
        blockchain.requestTransactionRegistration(seventhTransaction));

    assertEquals(10, firstNode.getBalance());
    assertEquals(40, secondNode.getBalance());

    Transaction eighthTransaction = new SmartContractTransaction(firstNodeAddress,
        sixthTransaction.getLinkedSmartContractAddress(), 5, "transferToB", paramTypes, args);

    assertEquals(true,
        blockchain.requestTransactionRegistration(eighthTransaction));

    assertEquals(5, firstNode.getBalance());
    assertEquals(40, secondNode.getBalance());

  }
}
