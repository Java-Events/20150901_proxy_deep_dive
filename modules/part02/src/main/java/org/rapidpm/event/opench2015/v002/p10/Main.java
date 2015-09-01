package main.java.org.rapidpm.event.opench2015.v002.p10;

import org.rapidpm.event.opench2015.v002.AddAdapterBWL;
import org.rapidpm.event.opench2015.v002.Taschenrechner;
import org.rapidpm.event.opench2015.v002.TaschenrechnerSci;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by buergich on 01.09.15.
 */
public class Main {

    public static void main(String[] args) {

        final ClassLoader classLoader = Taschenrechner.class.getClassLoader();
        Class[] clzz = new Class[]{Taschenrechner.class};

        final Taschenrechner proxyInstance = (
                Taschenrechner.class.cast(
                        Proxy.newProxyInstance(classLoader,
                                clzz, new InvocationHandler() {

                                    private Taschenrechner rechner = new TaschenrechnerSci();
                                    private AddAdapterBWL adapterBWL = new AddAdapterBWL();

                                    @Override
                                    public Object invoke(final Object proxy,
                                                         final Method method,
                                                         final Object[] args) throws Throwable {

                                        System.out.println("Proxy called");

                                        Method[] methods = adapterBWL.getClass().getMethods();

                                        for (Method m : methods) {
                                            if (m.getName().equals(method.getName())) {
                                                return m.invoke(adapterBWL, args);
                                            }
                                        }
                                        return method.invoke(rechner, args);
                                    }
                                })));

        System.out.println(proxyInstance.add(1, 2));

        System.out.println(proxyInstance.sub(2, 1));

    }
}
