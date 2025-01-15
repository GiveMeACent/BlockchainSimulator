package SmartContract;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import Node.SimpleNode;
import Node.Node;

public class SmartContractTest {

  @Test
  void testSetParties() {
    Node firstNode = new SimpleNode();
    Node secondNode = new SimpleNode();

    SmartContractBase contract = new ExampleSmartContract();
    contract.setParties(List.of(firstNode, secondNode));
    assertEquals(List.of(firstNode, secondNode), contract.getParties());

  }
}
