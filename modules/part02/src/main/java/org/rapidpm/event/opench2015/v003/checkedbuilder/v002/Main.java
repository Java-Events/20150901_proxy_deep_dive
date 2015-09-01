package org.rapidpm.event.opench2015.v003.checkedbuilder.v002;

import java.util.Optional;

/**
 * Created by sven on 30.04.15.
 */
public class Main {
  public static void main(String[] args) {
    final Optional<DataHolder> holderOptional = DataHolder
        .newBuilder().withA(1).withB(1).withC(1).build();
    System.out.println("holderOptional.isPresent() = " + holderOptional.isPresent());
  }
}
