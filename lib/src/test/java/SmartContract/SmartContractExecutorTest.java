package SmartContract;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import Node.Node;
import Node.SimpleNode;

public class SmartContractExecutorTest {
  @Test
  void testEvaluateContractCost() {
    Node firstNode = new SimpleNode();
    Node secondNode = new SimpleNode();

    SmartContractBase contract = new ValidSmartContract();
    contract.setParties(List.of(firstNode, secondNode), null);

    assertEquals(15, SmartContractExecutor.evaluateContractCost(contract));
  }

  @Test
  void testEvaluateMethodCost() {
    Node firstNode = new SimpleNode();
    Node secondNode = new SimpleNode();

    SmartContractBase contract = new ValidSmartContract();
    contract.setParties(List.of(firstNode, secondNode), null);
    Class<?>[] paramTypes = new Class[1];
    paramTypes[0] = Integer.class;

    assertEquals(10, SmartContractExecutor.evaluateMethodCost(contract, "transferToB", paramTypes));
  }

  @Test
  void testInvokeContractMethod() {
    Node firstNode = new SimpleNode();
    Node secondNode = new SimpleNode();

    firstNode.setBalance(20);
    secondNode.setBalance(30);

    SmartContractBase contract = new ValidSmartContract();
    contract.setParties(List.of(firstNode, secondNode), null);
    Class<?>[] paramTypes = new Class[1];
    paramTypes[0] = Integer.class;
    Object[] args = { 10 };

    SmartContractExecutor.invokeContractMethod(contract, "transferToB", paramTypes, args);

    assertEquals(10, contract.getParties().get(0).getBalance());
    assertEquals(40, contract.getParties().get(1).getBalance());

    assertEquals(20, firstNode.getBalance());
    assertEquals(30, secondNode.getBalance());
  }
}
