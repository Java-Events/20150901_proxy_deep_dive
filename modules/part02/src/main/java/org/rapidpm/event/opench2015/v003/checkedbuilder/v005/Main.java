package org.rapidpm.event.opench2015.v003.checkedbuilder.v005;


import java.util.Optional;

/**
 * Created by sven on 30.04.15.
 */
public class Main {

  public static void main(String[] args) {

    //lambdas
    final DataHolder.Builder builder = DataHolder.newBuilder();

    final Optional<DataHolder> holderOptional = builder
        .withA(1).withB(1).withC(1).build();
    System.out.println("holderOptional.isPresent() = " + holderOptional.isPresent());

    //wrong, but no Validator added
    System.out.println("holderOptional.isPresent() = " + builder.withC(2).build().isPresent());

    builder
        .addValidator(new NotZeroValidator())
        .addValidator(dataHolder
            -> dataHolder.a + dataHolder.b + dataHolder.c == 3);
    System.out.println("holderOptional.isPresent() = " + builder.withC(2).build().isPresent());
    System.out.println("holderOptional.isPresent() = " + builder.withC(1).build().isPresent());

  }



}
