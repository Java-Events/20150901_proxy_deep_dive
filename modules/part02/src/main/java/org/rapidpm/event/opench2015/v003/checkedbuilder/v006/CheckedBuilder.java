package org.rapidpm.event.opench2015.v003.checkedbuilder.v006;


import org.rapidpm.event.opench2015.v003.checkedbuilder.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by sven on 30.04.15.
 */
public abstract class CheckedBuilder<B extends CheckedBuilder, T> {

  protected List<Validator<T>> validatorList = new ArrayList<>();

  public B addValidator(Validator<T> validator) {
    validatorList.add(validator);
    return (B) this;
  }

  private Optional<T> checkAndGet(T value) {
    return validatorList.stream()
        .filter(v -> !v.checkCombination(value))
        .map(v -> Optional.<T>empty()) //check false
        .findFirst()
        .orElse(Optional.of(value));
  }

  protected abstract T createInstance();

  //add manually - start
  public Optional<T> build() {
    return checkAndGet(this.createInstance());
  }
  //add manually - stop
}
