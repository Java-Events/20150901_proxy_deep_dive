package org.rapidpm.event.opench2015.v003.checkedbuilder;


/**
 * Created by sven on 30.04.15.
 */
@FunctionalInterface
public interface Validator<T> {
  boolean checkCombination(T dataHolder);
}
