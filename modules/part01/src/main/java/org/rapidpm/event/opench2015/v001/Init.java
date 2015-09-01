package org.rapidpm.event.opench2015.v001;

/**
 * Created by svenruppert on 01.09.15.
 */
public class Init {

  public static void main(String[] args) {
    final Init init = new Init();
  }

  public Init() {
    System.out.println(" constructor ");
  }

  static {
    System.out.println(" static 01");
  }
  static {
    System.out.println(" static 02");
  }

  {
    System.out.println(" non-static 01");
  }
  {
    System.out.println(" non-static 02");
  }

}
