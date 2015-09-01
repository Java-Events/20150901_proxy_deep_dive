package org.rapidpm.event.opench2015.v002;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by svenruppert on 01.09.15.
 */
public class Main {

  public static void main(String[] args) {
    final Taschenrechner proxy = makeProxy(Taschenrechner.class,
        new TaschenrechnerSci(),
        new AddAdapterBWL());

    System.out.println("proxy.add(1,1) = " + proxy.add(1, 1));
  }



  public static <P, A> P makeProxy(Class<P> subject, P realSubject, A adapterSubject) {
    final InvocationHandler h = new InvocationHandler() {
      private P original = realSubject;
      private A adapter = adapterSubject;

      private Map<MethodIdentifier, Object> methodAdapterMap = new HashMap<>();

      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (methodAdapterMap.isEmpty()){
          final Class<?> adapterSubjectClass = adapterSubject.getClass();
          final Method[] declaredMethods = adapterSubjectClass.getDeclaredMethods();
          for (Method declaredMethod : declaredMethods) {
            final MethodIdentifier methodKey = new MethodIdentifier(declaredMethod);
            methodAdapterMap.put(methodKey, adapter);
          }
        }
        final MethodIdentifier methodKey = new MethodIdentifier(method);
        if (methodAdapterMap.containsKey(methodKey)) {
          final Object adapter = methodAdapterMap.get(methodKey);
          final Class<?> adapterClass = adapter.getClass();
          final Method adapterMethod = adapterClass.getDeclaredMethod(
              methodKey.name, methodKey.parameters);

          return adapterMethod.invoke(adapter, args);
        } else {
          return method.invoke(original, args);
        }
      }
    };
    Object proxyInstance = Proxy.newProxyInstance(
        subject.getClassLoader(),
        new Class<?>[]{subject},
        h
    );
    return subject.cast(proxyInstance);
  }

  public static class MethodIdentifier {
    private final String name;
    private final Class[] parameters;

    public MethodIdentifier(Method m) {
      name = m.getName();
      parameters = m.getParameterTypes();
    }

    // we can save time by assuming that we only compare against
    // other MethodIdentifier objects
    public boolean equals(Object o) {
      MethodIdentifier mid = (MethodIdentifier) o;
      return name.equals(mid.name) &&
          Arrays.equals(parameters, mid.parameters);
    }

    public int hashCode() {
      return name.hashCode();
    }
  }


}
