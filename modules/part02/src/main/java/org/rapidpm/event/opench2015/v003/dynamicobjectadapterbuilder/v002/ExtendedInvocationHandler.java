package org.rapidpm.event.opench2015.v003.dynamicobjectadapterbuilder.v002;



import com.sun.istack.internal.NotNull;
import org.rapidpm.event.opench2015.v003.dynamicobjectadapterbuilder.model.MethodIdentifier;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sven on 12.05.15.
 */
public abstract class ExtendedInvocationHandler<T> implements InvocationHandler {

  private Map<MethodIdentifier, Method> adaptedMethods = new HashMap<>();
  private Map<MethodIdentifier, Object> adapters = new HashMap<>();
  private T original;

  public void setOriginal(T original) {
    this.original = original;
  }

  public void addAdapter(Object adapter) {
    final Class<?> adapterClass = adapter.getClass();
    Method[] methods = adapterClass.getDeclaredMethods();
    for (Method m : methods) {
      final MethodIdentifier key = new MethodIdentifier(m);
      adaptedMethods.put(key, m);
      adapters.put(key, adapter);
    }
  }

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

}
