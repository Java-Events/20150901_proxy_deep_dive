package org.rapidpm.event.opench2015.v003.checkedbuilder.v004;


import java.util.Optional;

/**
 * Created by sven on 30.04.15.
 */
public class Main {

  public static void main(String[] args) {
    classic();

    //lambdas
    final DataHolder.Builder builder = DataHolder.newBuilder();

    final Optional<DataHolder> holderOptional = builder
        .withA(1).withB(1).withC(1).build();
    System.out.println(".isPresent() = " + holderOptional.isPresent());

    //wrong, but no Validator added
    System.out.println(".isPresent() = " + builder.withC(2).build().isPresent());

    builder
        .addValidator(dataHolder -> {
          final boolean a = dataHolder.a != 0;
          final boolean b = dataHolder.b != 0;
          final boolean c = dataHolder.c != 0;
          return (a && b && c);
        })
        .addValidator(dataHolder -> dataHolder.a + dataHolder.b + dataHolder.c == 3);
    System.out.println(".isPresent() = " + builder.withC(2).build().isPresent());
    System.out.println(".isPresent() = " + builder.withC(1).build().isPresent());

  }

  private static void classic() {
    final DataHolder.Builder builder = DataHolder.newBuilder();

    final Optional<DataHolder> holderOptional = builder.withA(1).withB(1).withC(1).build();
    System.out.println(".isPresent() = " + holderOptional.isPresent());

    //wrong, but no Validator added
    System.out.println(".isPresent() = " + builder.withC(2).build().isPresent());


    builder
        .addValidator(new NotZeroValidator())
        .addValidator(new BusinessRule01Validator());
    System.out.println(".isPresent() = " + builder.withC(2).build().isPresent());
    System.out.println(".isPresent() = " + builder.withC(1).build().isPresent());
  }


}
