package org.rapidpm.event.opench2015.v001;

/**
 * Created by svenruppert on 01.09.15.
 */
public class Main {


  public static void main(String[] args) {
    new DemoInterfaceA() {
      @Override
      public void doWorkA() {

      }

      @Override
      public void doWorkB() {

      }
    };

    DemoInterfaceB demoInterfaceBa = new DemoInterfaceB(){
      @Override
      public void doWorkA(final String x) {

      }
    };
    DemoInterfaceB demoInterfaceBb = (hoppel) -> {

    };

    DemoInterfaceC demoInterfaceC = () -> {

    };


  }


  public static interface DemoInterfaceA {
    public void doWorkA();

    public void doWorkB();
  }

  public static interface DemoInterfaceC {
    public void doWorkA();

    public default void doWorkB() {
      System.out.println("LocalDateTime.now = " + System.nanoTime());
    }

    ;
  }


  public static interface DemoInterfaceB {
    public void doWorkA(String x);
  }

}
