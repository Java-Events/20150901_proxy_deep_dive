package org.rapidpm.event.opench2015.v002.p06;

import org.rapidpm.event.opench2015.v002.*;

/**
 * Created by svenruppert on 01.09.15.
 */
public class TaschenrechnerBWL implements Taschenrechner {

  private Taschenrechner delegator;

  @Override
  public Integer add(final Integer a, final Integer b) {
    return a + b + 120;
  }

  @Override
  public Integer sub(final Integer a, final Integer b) {
    return delegator.sub(a, b);
  }

  @Override
  public Integer mult(final Integer a, final Integer b) {
    return delegator.mult(a, b);
  }

  @Override
  public Integer div(final Integer a, final Integer b) {
    return delegator.div(a, b);
  }
}
