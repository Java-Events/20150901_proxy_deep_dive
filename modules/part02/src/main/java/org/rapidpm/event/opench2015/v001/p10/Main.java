package main.java.org.rapidpm.event.opench2015.v001.p10;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by buergich on 01.09.15.
 */
public class Main {

    public static void main(String[] args) {
        final ClassLoader classLoader = MyService.class.getClassLoader();
        Class[] clzz = new Class[]{MyService.class};

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

                                        System.out.println("Proxy called");

                                        MyService myService = strategyFactory.realSubject(serviceFactory);
                                        Object result = method.invoke(myService, args);

                                        System.out.println("Proxy finished");

                                        return result;
                                    }
                                })));

        System.out.println(proxyInstance.doWorkA());
        System.out.println(proxyInstance.doWorkB());
    }

    public static interface ServiceFactory {
        MyService createInstance();
    }

    public static interface ServiceStrategyFactory {
        MyService realSubject(ServiceFactory factory);
    }

    public static interface MyService {
        public String doWorkA();

        public String doWorkB();
    }

    public static class ServiceStrategyFactoryNotThreadSave implements ServiceStrategyFactory {

        MyService realSubject;

        @Override
        public MyService realSubject(final ServiceFactory factory) {
            if (realSubject == null) {
                System.out.println("Creating instance");
                realSubject = factory.createInstance();
            }
            return realSubject;
        }
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
