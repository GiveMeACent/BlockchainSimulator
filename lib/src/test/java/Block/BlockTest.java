package Block;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import Utils.HashUtils;

public class BlockTest {

  @Test
  void testBlockGetPreviousBlockHash() {
    Block blockOne = new Block("GENESIS_HASH", 0);
    assertEquals(blockOne.getPreviousBlockHash(), "GENESIS_HASH");
  }

  @Test
  void testBlockGetHeight() {
    Block blockOne = new Block("GENESIS_HASH", 0);
    assertEquals(blockOne.getHeight(), 0);
  }

  @Test
  void testBlockGetCurrentSize() {
    Block blockOne = new Block("GENESIS_HASH", 0);
    assertEquals(blockOne.getCurrentSize(), 0);
  }

  @Test
  void testBlockCalculateHash() {
    Block blockOne = new Block("GENESIS_HASH", 0);
    String timestamp = blockOne.getTimeStamp();
    String previousBlockHash = blockOne.getPreviousBlockHash();

    blockOne.calculateHash();
    String actualHash = blockOne.getHash();
    String expectedHash = HashUtils.hashSha256(previousBlockHash.concat(timestamp));

  }

}
