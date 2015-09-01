package org.rapidpm.event.opench2015.v001;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by svenruppert on 01.09.15.
 */
public class Main {


  public static void main(String[] args) {

    final ClassLoader classLoader = MyService.class.getClassLoader();

    Class[] clzz = new Class[1];
    clzz[0] = MyService.class;

    final MyService proxyInstance = (
        MyService.class.cast(
            Proxy.newProxyInstance(classLoader,
                clzz, new InvocationHandler() {

                  private ServiceA serviceA;
                  //@Inject Service service;

                  @Override
                  public Object invoke(final Object proxy,
                                       final Method method,
                                       final Object[] args) throws Throwable {
                    final boolean proxyClass = Proxy.isProxyClass(proxy.getClass());

                    System.out.println("proxyClass = " + proxyClass);
                    System.out.println("method = " + method);
                    System.out.println("args = " + args);

                    if (serviceA == null) serviceA = new ServiceA();

                    final Object invoke = method.invoke(serviceA, args);

                    return invoke;
                  }
                })));

    final String result = proxyInstance.doWorkA();
    System.out.println("result = " + result);

    final boolean proxyClass = Proxy.isProxyClass(proxyInstance.getClass());
    if (proxyClass){
      final InvocationHandler invocationHandler = Proxy.getInvocationHandler(proxyInstance);


    }


  }


  public static interface MyService {
    public String doWorkA();
    public String doWorkB();
  }

  public static class ServiceA implements MyService {
    @Override
    public String doWorkA() {
      return "ServiceA.doWorkA";
    }
    @Override
    public String doWorkB() {
      return "ServiceA.doWorkB";
    }
  }

}
