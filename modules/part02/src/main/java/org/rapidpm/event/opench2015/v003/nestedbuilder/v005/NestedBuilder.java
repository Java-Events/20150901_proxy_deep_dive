package org.rapidpm.event.opench2015.v003.nestedbuilder.v005;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by sven on 05.03.15.
 */
public abstract class NestedBuilder<T, V> {

  /**
   * To get the parent builder
   *
   * @return T the instance of the parent builder
   */
  public T done() {
    Class<?> parentClass = parent.getClass();
    try {
      V build = this.build();
      String methodname = "with" + build.getClass().getSimpleName();
      Method method = parentClass.getDeclaredMethod(methodname, build.getClass());
      method.invoke(parent, build);
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }
    return parent;
  }

  public abstract V build();

  protected T parent;

  /**
   * @param parent
   * @return
   */
  public <P extends NestedBuilder<T, V>> P withParentBuilder(T parent) {
    this.parent = parent;
    return (P) this;
  }


}
