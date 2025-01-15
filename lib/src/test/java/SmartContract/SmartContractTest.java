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

    SmartContractBase contract = new ValidSmartContract();
    contract.setParties(List.of(firstNode, secondNode), null);
    assertEquals(contract.getParties(), contract.getParties());

  }
}
