package org.rapidpm.event.opench2015.v003.dynamicobjectadapterbuilder.v002;

import java.lang.reflect.Proxy;

/**
 * Created by sven on 12.05.15.
 */
public abstract class AdapterBuilder<T> {
//  private Class<T> target;
//
//  public <B extends AdapterBuilder<T>> B withTarget(Class<T> target) {
//    this.target = target;
//    return (B) this;
//  }

  public T buildForTarget(Class<T> target) {
    return (T) Proxy.newProxyInstance(
        target.getClassLoader(),
        new Class[]{target},
        getInvocationHandler()
    );
  }

  protected abstract <I extends ExtendedInvocationHandler<T>> I getInvocationHandler();
}
