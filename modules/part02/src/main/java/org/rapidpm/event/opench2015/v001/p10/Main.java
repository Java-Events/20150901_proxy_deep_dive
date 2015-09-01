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
                                        return strategyFactory.realSubject(serviceFactory).doWorkA();
                                    }
                                })));

        String response = proxyInstance.doWorkA();
        System.out.println(response);
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
