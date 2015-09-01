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

                                        if (method.getName().equals("add")) {
                                            return adapterBWL.add(Integer.class.cast(args[0]), Integer.class.cast(args[1]));
                                        } else {
                                            return method.invoke(rechner, args);
                                        }
                                    }
                                })));

        System.out.println(proxyInstance.add(1, 2));

        System.out.println(proxyInstance.sub(2, 1));

    }
}
