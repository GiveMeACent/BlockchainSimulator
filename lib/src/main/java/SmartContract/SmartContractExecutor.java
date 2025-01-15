package SmartContract;

import java.lang.reflect.*;

public class SmartContractExecutor {

  public static final Integer BASE_COST = 5;

  public static Integer evaluateContractCost(SmartContractBase contract) {
    Class<? extends SmartContractBase> contractClass = contract.getClass();

    Integer methodsNumber = contractClass.getDeclaredMethods().length;
    Integer fieldsNumber = contractClass.getDeclaredFields().length;

    return (methodsNumber + fieldsNumber) * BASE_COST;
  }

  public static Integer evaluateMethodCost(SmartContractBase contract, String methodName, Class<?>... parameters) {
    try {
      Method m = contract.getClass().getMethod(methodName, parameters);
      return (m.getParameterCount() + m.getModifiers()) * BASE_COST;
    } catch (Exception e) {
      return 0;
    }

  }

  public static void invokeContractMethod(SmartContractBase contract, String methodName, Class<?> parameters[],
      Object[] args) {
    Class<? extends SmartContractBase> contractClass = contract.getClass();
    try {
      Method m = contractClass.getMethod(methodName, parameters);
      m.invoke(contract, args);
    } catch (Exception e) {
      return;
    }

  }
}
