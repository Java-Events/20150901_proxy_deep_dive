package org.rapidpm.event.opench2015.v003.checkedbuilder.v004;


import org.rapidpm.event.opench2015.v003.checkedbuilder.Validator;

/**
 * Created by sven on 30.04.15.
 */
public class BusinessRule01Validator implements Validator<DataHolder> {
  @Override
  public boolean checkCombination(DataHolder dataHolder) {
    return dataHolder.a + dataHolder.b + dataHolder.c == 3;
  }
}
