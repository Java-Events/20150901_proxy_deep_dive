package org.rapidpm.event.opench2015.v003.checkedbuilder.v001;

/**
 * Created by sven on 30.04.15.
 */
public class Main {
  public static void main(String[] args) {

    final DataHolder build = DataHolder.newBuilder()
        .withA(1).withB(1).withC(1).build();
    final boolean b = new NotZeroValidator().checkCombination(build);
    System.out.println("b = " + b);
  }
}
