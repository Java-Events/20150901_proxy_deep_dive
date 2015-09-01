package main.java.org.rapidpm.event.opench2015.v001.p09;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by legap on 01.09.15.
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

                                    private ServiceStrategyFactory strategyFactory = new ServiceStrategyFactoryNotThreadSave();
                                    private ServiceFactory serviceFactory = ServiceA::new;

                                    @Override
                                    public Object invoke(final Object proxy,
                                                         final Method method,
                                                         final Object[] args) throws Throwable {
                                        final boolean proxyClass = Proxy.isProxyClass(proxy.getClass());

                                        System.out.println("proxyClass = " + proxyClass);
                                        System.out.println("method = " + method);
                                        System.out.println("args = " + args);

                                        final ServiceA serviceA = new ServiceA();


                                        MyService myService = strategyFactory.realSubject(serviceFactory);
                                        final Object invoke = method.invoke(myService, args);
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

    public static interface ServiceFactory {
        MyService createInstance();
    }

    public static interface ServiceStrategyFactory {
        MyService realSubject(ServiceFactory factory);
    }

    public static class ServiceStrategyFactoryNotThreadSave implements ServiceStrategyFactory {

        MyService realSubject;

        @Override
        public MyService realSubject(final ServiceFactory factory) {
            if (realSubject == null) {
                System.out.println("ServiceStrategyFactory -> creating Service");
                realSubject = factory.createInstance();
            }
            return realSubject;
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
