package org.rapidpm.event.opench2015.v001.sru.v002;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Sven Ruppert on 19.12.2014.
 *
 * Funktioniert nicht symmetrisch !!!
 *
 *
 *
 */
public class Proxies {

  public static <P> P make(Class<P> subject, P impl) {
    final DelegationHandler<P> dh = new DelegationHandler<>(impl);
    return subject.cast(Proxy.newProxyInstance(
        subject.getClassLoader(),
        new Class[]{subject}, dh));
  }

  private static class DelegationHandler<P> implements InvocationHandler {
    private final P wrapped;

    public DelegationHandler(P wrapped) {
      this.wrapped = wrapped;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      if ("equals".equals(method.getName())
          && args != null
          && args.length == 1
          && args[0].getClass() == Object.class
          ) {
        return equalsInternal(proxy, args[0]);
      }
      if ("hashCode".equals(method.getName())
          && (args != null)
          && (args.length == 0)) {
        return wrapped.hashCode();
      }
      return method.invoke(wrapped, args);
    }


    private boolean equalsInternal(Object me, Object other) {
      if (other == null) {
        return false;
      }
      if (other.getClass() != me.getClass()) {/* ??? */
        return false;
      }
      final InvocationHandler handler = Proxy.getInvocationHandler(other);
      return handler instanceof DelegationHandler && ((DelegationHandler) handler).wrapped.equals(wrapped);
    }
  }

}
