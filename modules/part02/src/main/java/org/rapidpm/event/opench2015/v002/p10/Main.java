package main.java.org.rapidpm.event.opench2015.v002.p10;

import org.rapidpm.event.opench2015.v002.AddAdapterBWL;
import org.rapidpm.event.opench2015.v002.Taschenrechner;
import org.rapidpm.event.opench2015.v002.TaschenrechnerSci;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by buergich on 01.09.15.
 */
public class Main {

    public static void main(String[] args) {
        final Taschenrechner proxy = makeProxy(Taschenrechner.class,
                new TaschenrechnerSci(),
                new AddAdapterBWL());

        System.out.println("proxy.add(1,1) = " + proxy.add(1, 1));
        System.out.println("proxy.add(1,5) = " + proxy.add(1, 5));
        System.out.println("proxy.sub(1,1) = " + proxy.sub(1, 1));
        System.out.println("proxy.sub(5,1) = " + proxy.sub(5, 1));
    }



    public static <P, A> P makeProxy(Class<P> subject, P realSubject, A adapterSubject) {
        final InvocationHandler h = new InvocationHandler() {
            private P original = realSubject;
            private A adapter = adapterSubject;

            private Map<MethodKey, ExecutingAdapter> methodAdapterMap = new HashMap<>();

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (methodAdapterMap.isEmpty()){
                    final Class<?> adapterSubjectClass = adapterSubject.getClass();
                    final Method[] declaredMethods = adapterSubjectClass.getDeclaredMethods();
                    for (Method declaredMethod : declaredMethods) {
                        final MethodKey methodKey = new MethodKey();
                        methodKey.setMethod(declaredMethod);
                        ExecutingAdapter executingAdapter = new ExecutingAdapter();
                        executingAdapter.setAdapter(adapter);
                        executingAdapter.setMethod(declaredMethod);
                        methodAdapterMap.put(methodKey, executingAdapter);
                    }
                }
                final MethodKey methodKey = new MethodKey();
                methodKey.setMethod(method);
                if (methodAdapterMap.containsKey(methodKey)) {
                    final ExecutingAdapter adapterObject = methodAdapterMap.get(methodKey);
                    return adapterObject.executeAdapter(args);
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
            final MethodKey otherKey = (MethodKey) o;

            if (!method.getName().equals(otherKey.method.getName())) {
                return false;
            }

            if (method.getParameterCount() != otherKey.method.getParameterCount()) {
                return false;
            }
            Class<?>[] parameterTypes = method.getParameterTypes();
            Class<?>[] otherParameterTypes = otherKey.method.getParameterTypes();
            for (int i = 0; i < method.getParameterCount(); i++) {
                if (!parameterTypes[i].equals(otherParameterTypes[i])) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            return Objects.hash(method.getName());
        }

        private Method getMethod() {
            return method;
        }

        private void setMethod(final Method method) {
            this.method = method;
        }
    }

    public static class ExecutingAdapter {

        private Method method;
        private Object adapter;

        public void setMethod(Method method) {
            this.method = method;
        }

        public void setAdapter(Object adapter) {
            this.adapter = adapter;
        }

        public Object executeAdapter(Object... args) throws InvocationTargetException, IllegalAccessException {
            return method.invoke(adapter, args);
        }

    }

}
