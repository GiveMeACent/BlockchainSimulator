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

  public static Integer evaluateMethodCost(SmartContractBase contract, String methodName)
      throws NoSuchMethodException, SecurityException {
    Method m = contract.getClass().getMethod(methodName);

    return (m.getParameterCount() + m.getModifiers()) * BASE_COST;
  }

  public static void invokeContractMethod(SmartContractBase contract, String methodName, Class<?> parameters,
      Object... args)
      throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
    Class<? extends SmartContractBase> contractClass = contract.getClass();
    Method m = contractClass.getDeclaredMethod(methodName, parameters);
    m.invoke(m, args);
  }
}
