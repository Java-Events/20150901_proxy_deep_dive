package org.rapidpm.event.opench2015.v003.nestedbuilder.v002;

import java.util.List;

/**
 * Created by sven on 05.03.15.
 */
public class Main {
  public static void main(String[] args) {

    Engine engine = Engine.newBuilder().withPower(100).withType(5).build();

    Wheel wheel1 = Wheel.newBuilder().withType(2).withColour(3).withSize(4).build();
    Wheel wheel2 = Wheel.newBuilder().withType(2).withColour(3).withSize(4).build();
    Wheel wheel3 = Wheel.newBuilder().withType(2).withColour(3).withSize(4).build();

//        List<Wheel> wheels = new ArrayList<>();
//        wheels.add(wheel1);
//        wheels.add(wheel2);
//        wheels.add(wheel3);

    List<Wheel> wheelList = WheelListBuilder.newBuilder()
        .withNewList()
        .addWheel(wheel1)
        .addWheel(wheel2)
        .addWheel(wheel3)
        .build();

    Car car = Car.newBuilder()
        .withEngine(engine)
        .withWheelList(wheelList)
        .build();

    System.out.println("car = " + car);
  }
}
