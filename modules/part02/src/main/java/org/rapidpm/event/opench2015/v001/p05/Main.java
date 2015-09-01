package org.rapidpm.event.opench2015.v001.p05;

import java.lang.reflect.Proxy;
import java.util.function.Supplier;

/**
 * @author Dominik Menzi
 */
public class Main {


    public static void main(String[] args) {

        final ClassLoader classLoader = MyService.class.getClassLoader();

        Class[] clzz = new Class[1];
        clzz[0] = MyService.class;

        final Supplier<ServiceA> supplier = new SynchronizingSupplier<>(new CachingSupplier<>(ServiceA::new));

        final MyService proxyInstance = (
                MyService.class.cast(
                        Proxy.newProxyInstance(classLoader,
                                clzz, (proxy, method, args1) -> {
                                    final boolean proxyClass = Proxy.isProxyClass(proxy.getClass());

                                    System.out.println("proxyClass = " + proxyClass);
                                    System.out.println("method = " + method);
                                    System.out.println("args = " + args1);

                                    final ServiceA instance = supplier.get();

                                    return method.invoke(instance, args1);
                                })));

        System.out.println(proxyInstance.doWorkA());
        System.out.println(proxyInstance.doWorkB());
    }


    private static class SynchronizingSupplier<T> implements Supplier<T> {

        private final Supplier<T> delegate;

        private SynchronizingSupplier(Supplier<T> delegate) {
            this.delegate = delegate;
        }

        @Override
        public synchronized T get() {
            return delegate.get();
        }
    }

    private static class CachingSupplier<T> implements Supplier<T> {

        private final Supplier<T> delegate;

        private T instance;

        private CachingSupplier(Supplier<T> delegate) {
            this.delegate = delegate;
        }

        @Override
        public T get() {
            if (instance == null) {
                instance = delegate.get();
            }
            return instance;
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
