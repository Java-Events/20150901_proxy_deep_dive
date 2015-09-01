package org.rapidpm.event.opench2015.v003.nestedbuilder.v003;

import java.util.List;

/**
 * Created by sven on 05.03.15.
 */
public class Main {
  public static void main(String[] args) {

    Engine engine = Engine.newBuilder().withPower(100).withType(5).build();


    WheelListBuilder wheelListBuilder = WheelListBuilder.newBuilder().withNewList();
//                .addWheel(wheel1)
//                .addWheel(wheel2)
//                .addWheel(wheel3)
//                .build();
    //@formatter:off
    List<Wheel> wheels = wheelListBuilder
        .addWheel().withType(1).withSize(2).withColour(2).addWheelToList()
        .addWheel().withType(1).withSize(2).withColour(2).addWheelToList()
        .addWheel().withType(1).withSize(2).withColour(2).addWheelToList()
        .addWheel().withType(1).withSize(2).withColour(2).addWheelToList()
        .build();

    //@formatter:on
    Car car = Car.newBuilder()
        .withEngine(engine)
        .withWheelList(wheels)
        .build();

    System.out.println("car = " + car);
  }
}
