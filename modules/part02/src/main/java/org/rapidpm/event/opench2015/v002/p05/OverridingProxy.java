package org.rapidpm.event.opench2015.v002.p05;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author Dominik Menzi
 */
public class OverridingProxy {

    public static <T> T create(Class<T> clazz, T instance, Object... overriders) {
        final HashMap<FunctionKey, OverridingMethod> functionToMethod = compileOverridingMethods(overriders);

        return (T) Proxy.newProxyInstance(OverridingProxy.class.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                final FunctionKey key = new FunctionKey(method);
                if (functionToMethod.containsKey(key)) {
                    final OverridingMethod overridingMethod = functionToMethod.get(key);
                    return overridingMethod.method.invoke(overridingMethod.implementingObject, args);
                }
                else {
                    return method.invoke(instance, args);
                }
            }
        });
    }

    private static HashMap<FunctionKey, OverridingMethod> compileOverridingMethods(Object[] overriders) {
        final HashMap<FunctionKey, OverridingMethod> functionToMethod = new HashMap<>();
        for (Object overrider : overriders) {
            for (Method method : overrider.getClass().getDeclaredMethods()) {
                final FunctionKey functionKey = new FunctionKey(method);
                functionToMethod.putIfAbsent(functionKey, new OverridingMethod(overrider, method));
            }
        }
        return functionToMethod;
    }

    private static class OverridingMethod {
        private final Object implementingObject;
        private final Method method;

        private OverridingMethod(Object implementingObject, Method method) {
            this.implementingObject = implementingObject;
            this.method = method;
        }
    }

    private static class FunctionKey {
        private final String name;
        private final Class[] parameterTypes;

        private FunctionKey(Method method) {
            this.name = method.getName();
            this.parameterTypes = method.getParameterTypes();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            FunctionKey that = (FunctionKey) o;
            return Objects.equals(name, that.name) &&
                    Arrays.deepEquals(parameterTypes, that.parameterTypes);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name) + Arrays.hashCode(parameterTypes);
        }

        @Override
        public String toString() {
            return "FunctionKey{" +
                    "name='" + name + '\'' +
                    ", parameterTypes=" + Arrays.toString(parameterTypes) +
                    '}';
        }
    }
}
