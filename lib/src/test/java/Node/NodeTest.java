package Node;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class NodeTest {
  @Test
  void testNodeSetBalance() {
    Node newNode = new SimpleNode();
    newNode.setBalance(10);

    Integer nodeBalance = newNode.getBalance();
    assertEquals(nodeBalance, 10);
  }

  @Test
  void testNodeSetValidator() {
    Node newNode = new SimpleNode();
    newNode.setValidator(true);

    boolean isValidator = newNode.getValidator();
    assertEquals(isValidator, true);
  }

  @Test
  void testNodePutStake() {
    Node newNode = new ValidatorNode();
    newNode.putStake(30);

    Integer stake = newNode.getStakeValue();
    assertEquals(stake, 30);
  }

  @Test
  void testNodeRewardPenalize() {
    Node newNode = new ValidatorNode();
    newNode.putStake(30);

    newNode.reward(10);
    Integer stake = newNode.getStakeValue();
    assertEquals(stake, 40);

    newNode.penalize(10);
    stake = newNode.getStakeValue();
    assertEquals(stake, 30);
  }
}
