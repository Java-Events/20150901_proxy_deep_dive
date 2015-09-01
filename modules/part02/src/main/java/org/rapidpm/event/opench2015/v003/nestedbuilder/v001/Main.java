package org.rapidpm.event.opench2015.v003.nestedbuilder.v001;

import java.util.ArrayList;
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

        List<Wheel> wheels = new ArrayList<>();
        wheels.add(wheel1);
        wheels.add(wheel2);
        wheels.add(wheel3);

        Car car = Car.newBuilder()
                .withEngine(engine)
                .withWheelList(wheels)
                .build();

        System.out.println("car = " + car);
    }
}
