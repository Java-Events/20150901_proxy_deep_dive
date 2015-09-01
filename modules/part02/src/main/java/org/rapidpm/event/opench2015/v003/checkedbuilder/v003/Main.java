package org.rapidpm.event.opench2015.v003.checkedbuilder.v003;

import java.util.Optional;

/**
 * Created by sven on 30.04.15.
 */
public class Main {

  public static void main(String[] args) {
    final DataHolder.Builder builder = DataHolder.newBuilder();

    final Optional<DataHolder> holderOptional = builder
        .withA(1).withB(1).withC(1).build();
    System.out.println("holderOptional.isPresent() = " + holderOptional.isPresent());

    System.out.println("holderOptional.isPresent() = " + builder
        .withC(2).build().isPresent());
  }


}
