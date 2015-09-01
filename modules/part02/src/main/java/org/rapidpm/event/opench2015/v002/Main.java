package org.rapidpm.event.opench2015.v002;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
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

      private Map<MethodKey, Object> methodAdapterMap = new HashMap<>();

      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (methodAdapterMap.isEmpty()){
          final Class<?> adapterSubjectClass = adapterSubject.getClass();
          final Method[] declaredMethods = adapterSubjectClass.getDeclaredMethods();
          for (Method declaredMethod : declaredMethods) {
            final MethodKey methodKey = new MethodKey();
            methodKey.setMethod(declaredMethod);
            methodAdapterMap.put(methodKey, adapter);
          }
        }
        final MethodKey methodKey = new MethodKey();
        methodKey.setMethod(method);
        if (methodAdapterMap.containsKey(methodKey)) {
          final Object adapter = methodAdapterMap.get(methodKey);
          return method.invoke(adapter, args);
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

  public static class MethodKey {

    private Method method;

    @Override
    public boolean equals(final Object o) {
      if (this == o) return true;
      if (!(o instanceof MethodKey)) return false;
      final MethodKey methodKey = (MethodKey) o;
      return Objects.equals(method, methodKey.method);
    }

    @Override
    public int hashCode() {
      return Objects.hash(method);
    }

    private Method getMethod() {
      return method;
    }

    private void setMethod(final Method method) {
      this.method = method;
    }
  }


}
