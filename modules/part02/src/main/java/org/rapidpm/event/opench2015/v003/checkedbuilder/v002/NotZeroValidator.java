package org.rapidpm.event.opench2015.v003.checkedbuilder.v002;


import org.rapidpm.event.opench2015.v003.checkedbuilder.Validator;

/**
 * Created by sven on 30.04.15.
 */
public class NotZeroValidator implements Validator<DataHolder> {
  @Override
  public boolean checkCombination(DataHolder dataHolder) {
    final boolean a = dataHolder.a != 0;
    final boolean b = dataHolder.b != 0;
    final boolean c = dataHolder.c != 0;
    return (a && b && c);
  }
}
