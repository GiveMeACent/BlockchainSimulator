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
    assertEquals(nodeOne.getBalance(), blockchain.getNode(nodeOneAddress).getBalance());
  }

  @Test
  void functionalityTest() {
    // Setting up the context
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

    // Should correctly registrate a valid Monetary Transaction

    Transaction firstTransaction = new MonetaryTransaction(5, firstNodeAddress, secondNodeAddress, 5);

    assertEquals(true, blockchain.requestTransactionRegistration(firstTransaction));
    assertEquals(0, firstNode.getBalance());
    assertEquals(25, secondNode.getBalance());
    assertEquals(65, fourthValidatorNode.getStakeValue());

    // Should correctly refuse an invalid monetary transaction

    firstNode.setBalance(10);
    secondNode.setBalance(20);
    firstValidatorNode.putStake(70);

    assertEquals(true, blockchain.requestTransactionRegistration(firstTransaction));
    assertEquals(75, firstValidatorNode.getStakeValue());

    Transaction secondTransaction = new MonetaryTransaction(5, firstNodeAddress, secondNodeAddress, 30);

    assertEquals(false, blockchain.requestTransactionRegistration(secondTransaction));

    // Should correctly refuse an invalid monetary transaction due to unexistent
    // recipient

    Transaction thirdTransaction = new MonetaryTransaction(5, firstNodeAddress, "FAKE_ADDRESS", 30);

    assertEquals(false, blockchain.requestTransactionRegistration(thirdTransaction));

    // Should correctly refuse an invalid monetary transaction due to unexistent
    // caller

    Transaction fourthTransaction = new MonetaryTransaction(5, "FAKE_ADDRESS", secondNodeAddress, 30);

    assertEquals(false, blockchain.requestTransactionRegistration(fourthTransaction));

    // Should register transactions correctly

    List<Transaction> firstBlockTransactions = blockchain.getTransactions(0);
    List<Transaction> secondBlockTransactions;
    List<Transaction> expectedFirstBlockTransaction = List.of(firstTransaction, firstTransaction);
    List<Transaction> expectedSecondBlockTransaction = List.of(firstTransaction);

    for (int i = 0; i < expectedFirstBlockTransaction.size(); i++)
      assertEquals(expectedFirstBlockTransaction.get(i).getAddress(), firstBlockTransactions.get(i).getAddress());

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

    for (int i = 0; i < expectedFirstBlockTransaction.size(); i++)
      assertEquals(expectedFirstBlockTransaction.get(i).getAddress(), firstBlockTransactions.get(i).getAddress());

    assertEquals(true, blockchain.requestTransactionRegistration(firstTransaction));

    secondBlockTransactions = blockchain.getTransactions(1);
    expectedSecondBlockTransaction = List.of(firstTransaction);

    for (int i = 0; i < expectedSecondBlockTransaction.size(); i++)
      assertEquals(expectedSecondBlockTransaction.get(i).getAddress(), secondBlockTransactions.get(i).getAddress());

    // Should correctly refuse an invalid deployment transaction

    SmartContractBase validContract = new ValidSmartContract();
    validContract.setPartiesAddresses(List.of(firstNodeAddress, secondNodeAddress));

    firstNode.setBalance(15);
    secondNode.setBalance(20);

    Transaction fifthTransaction = new DeploymentTransaction(firstNodeAddress, 6, validContract);
    assertEquals(false, blockchain.requestTransactionRegistration(fifthTransaction));

    // Should correctly register a valid deployment transaction

    firstNode.setBalance(21);
    assertEquals(true, blockchain.requestTransactionRegistration(fifthTransaction));
    secondBlockTransactions = blockchain.getTransactions(1);
    assertEquals(fifthTransaction.getAddress(), secondBlockTransactions.getLast().getAddress());
    assertEquals(fifthTransaction.getLinkedSmartContract(), validContract);
    assertEquals(0, firstNode.getBalance());

    // Should correctly register a valid deployment transaction with an invalid
    // smart contract associated

    firstNode.setBalance(250);
    secondNode.setBalance(20);
    SmartContractBase maliciousContract = new MaliciousSmartContract();
    maliciousContract.setPartiesAddresses(List.of(firstNodeAddress, secondNodeAddress));
    Transaction sixthTransaction = new DeploymentTransaction(firstNodeAddress, 5, maliciousContract);
    assertEquals(true, blockchain.requestTransactionRegistration(sixthTransaction));

    // Should correctly register a valid smart contract transaction with an valid
    // smart contract associated

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

    // Should correctly refuse a valid smart contract transaction with an invalid
    // smart contract associated

    Transaction eighthTransaction = new SmartContractTransaction(firstNodeAddress,
        sixthTransaction.getLinkedSmartContractAddress(), 5, "transferToB", paramTypes, args);

    assertEquals(false,
        blockchain.requestTransactionRegistration(eighthTransaction));

    assertEquals(10, firstNode.getBalance());
    assertEquals(40, secondNode.getBalance());

  }
}
