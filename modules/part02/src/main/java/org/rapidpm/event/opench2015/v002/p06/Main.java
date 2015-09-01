package org.rapidpm.event.opench2015.v002.p06;

import org.rapidpm.event.opench2015.v002.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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
            private Map<MethodKey, Method> methodMap = new HashMap<>();

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (methodAdapterMap.isEmpty()) {
                    final Class<?> adapterSubjectClass = adapterSubject.getClass();
                    final Method[] declaredMethods = adapterSubjectClass.getDeclaredMethods();
                    for (Method declaredMethod : declaredMethods) {
                        final MethodKey methodKey = new MethodKey();
                        methodKey.setMethod(declaredMethod);
                        methodAdapterMap.put(methodKey, adapter);
                        methodMap.put(methodKey, declaredMethod);
                    }
                }
                final MethodKey methodKey = new MethodKey();
                methodKey.setMethod(method);
                if (methodAdapterMap.containsKey(methodKey)) {
                    final Method realMethod = methodMap.get(methodKey);
                    final Object adapter = methodAdapterMap.get(methodKey);
                    return realMethod.invoke(adapter, args);
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

        private String methodSignature;

        private String calculateMethodSignature() {
            String result = method.getName();
            result += "::";
            for (Parameter p : method.getParameters()) {
                result += p.getType().toString();
                result += "::";
            }
            return result;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 79 * hash + Objects.hashCode(this.methodSignature);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final MethodKey other = (MethodKey) obj;
            if (!Objects.equals(this.methodSignature, other.methodSignature)) {
                return false;
            }
            return true;
        }

        private Method getMethod() {
            return method;
        }

        private void setMethod(final Method method) {
            this.method = method;
            methodSignature = calculateMethodSignature();
        }
    }
  
}
