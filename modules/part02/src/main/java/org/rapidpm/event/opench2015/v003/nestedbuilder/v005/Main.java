package org.rapidpm.event.opench2015.v003.nestedbuilder.v005;

/**
 * Created by sven on 05.03.15.
 */
public class Main {
  public static void main(String[] args) {
    Parent build = Parent.newBuilder()
        .addKidA().withNote("A").done()
        .addKidB().withNote("B").done()
        .build();
    System.out.println("build = " + build);
  }
}
