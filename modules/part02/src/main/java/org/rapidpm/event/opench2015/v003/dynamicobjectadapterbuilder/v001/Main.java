package org.rapidpm.event.opench2015.v003.dynamicobjectadapterbuilder.v001;



import com.sun.istack.internal.NotNull;
import org.rapidpm.event.opench2015.v003.dynamicobjectadapterbuilder.model.MethodIdentifier;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sven on 08.05.15.
 */
public class Main {


  public static void main(String[] args) {


    Service service = AdapterBuilder.<Service>newBuilder()
        .withTarget(Service.class)
        .withOriginal(new ServiceImpl())
        .withDoWork_A((txt) -> txt + "_part")
        .build();

    System.out.println("service.doWork_A(\"Hallo Adapter\") = " + service.doWork_A("Hallo Adapter"));
  }


  public interface Service {
    String doWork_A(String txt);

    String doWork_B(String txt);
  }

  public static class ServiceImpl implements Service {
    @Override
    public String doWork_A(String txt) {
      return "doWorkd_A_Original";
    }

    @Override
    public String doWork_B(String txt) {
      return "doWorkd_B_Original";
    }
  }

  //generieren ?
  public interface ServiceAdapter_A {
    String doWork_A(String txt);
  }

  public interface ServiceAdapter_B {
    String doWork_B(String txt);
  }

  public static class AdapterBuilder<T> {

    private Map<MethodIdentifier, Method> adaptedMethods = new HashMap<>();
    private Map<MethodIdentifier, Object> adapters = new HashMap<>();

    private T original;
    private Class<T> target;

    private void addAdapter(Object adapter) {
      final Class<?> adapterClass = adapter.getClass();
      Method[] methods = adapterClass.getDeclaredMethods();
      for (Method m : methods) {
        final MethodIdentifier key = new MethodIdentifier(m);
        adaptedMethods.put(key, m);
        adapters.put(key, adapter);
      }
    }

    public static <T> AdapterBuilder<T> newBuilder() {
      return new AdapterBuilder<>();
    }

    public AdapterBuilder<T> withOriginal(T original) {
      this.original = original;
      return this;
    }

    public AdapterBuilder<T> withTarget(Class<T> target) {
      this.target = target;
      return this;
    }


    public AdapterBuilder<T> withDoWork_A(ServiceAdapter_A adapter) {
      addAdapter(adapter);
      return this;
    }

    public AdapterBuilder<T> withDoWork_B(ServiceAdapter_B adapter) {
      addAdapter(adapter);
      return this;
    }

    public T build() {
      final InvocationHandler invocationHandler = new InvocationHandler() {
        private Map<MethodIdentifier, Method> adaptedMethods = AdapterBuilder.this.adaptedMethods;
        private Map<MethodIdentifier, Object> adapters = AdapterBuilder.this.adapters;

        private final T original = AdapterBuilder.this.original;

        @Override
        public Object invoke(Object proxy, @NotNull Method method, Object[] args) throws Throwable {
          try {
            final MethodIdentifier key = new MethodIdentifier(method);
            Method other = adaptedMethods.get(key);
            if (other != null) {
              return other.invoke(adapters.get(key), args);
            } else {
              return method.invoke(original, args);
            }
          } catch (InvocationTargetException e) {
            throw e.getTargetException();
          }
        }
      };

      return (T) Proxy.newProxyInstance(
          target.getClassLoader(),
          new Class[]{target},
          invocationHandler
      );
    }
  }
}
