package Transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import SmartContract.SmartContractBase;
import SmartContract.ValidSmartContract;

public class TransactionTest {
  @Test
  void testMonetaryTransaction() {
    Transaction trx = new MonetaryTransaction(5, "address_one", "address_two", 10);
    trx.setAddress("transaction_address");
    assertEquals("address_one", trx.getCallerAddress());
    assertEquals("address_two", trx.getRecipientAddress());
    assertEquals(5, trx.getFee());
    assertEquals(10, trx.getAmountToTransfer());
    assertEquals("transaction_address", trx.getAddress());
    assertEquals(TransactionType.MONETARY, trx.getType());
  }

  @Test
  void testDeploymentTransaction() {
    SmartContractBase contract = new ValidSmartContract();
    Transaction trx = new DeploymentTransaction("address_one", 10, contract);
    assertEquals("address_one", trx.getCallerAddress());
    assertEquals(10, trx.getFee());
    assertEquals(contract, trx.getLinkedSmartContract());
    assertEquals(TransactionType.SMART_CONTRACT_DEPLOY, trx.getType());
  }

  @Test
  void testSmartContractTransaction() {
    Class<?>[] paramTypes = new Class[2];
    paramTypes[0] = Integer.class;
    paramTypes[1] = String.class;
    Object[] args = { 10, "example" };

    Transaction trx = new SmartContractTransaction("address_one", "contract_one", 10, "exampleMethod", paramTypes,
        args);

    assertEquals("address_one", trx.getCallerAddress());
    assertEquals(10, trx.getFee());
    assertEquals(paramTypes, trx.getParameters());
    assertEquals(args, trx.getArgs());
    assertEquals(TransactionType.SMART_CONTRACT_EXECUTE, trx.getType());
  }
}
