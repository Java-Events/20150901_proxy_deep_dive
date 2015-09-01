package org.rapidpm.event.opench2015.v002;

/**
 * Created by svenruppert on 01.09.15.
 */
public class TaschenrechnerSci implements Taschenrechner {
  @Override
  public Integer add(final Integer a, final Integer b) {
    return a+b;
  }

  @Override
  public Integer sub(final Integer a, final Integer b) {
    return a-b;
  }

  @Override
  public Integer mult(final Integer a, final Integer b) {
    return a * b;
  }

  @Override
  public Integer div(final Integer a, final Integer b) {
    return a / b;
  }
}
