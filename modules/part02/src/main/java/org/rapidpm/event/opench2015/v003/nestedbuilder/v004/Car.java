package org.rapidpm.event.opench2015.v003.nestedbuilder.v004;

import java.util.List;

/**
 * Created by sven on 05.03.15.
 */
public class Car {

    private Engine engine;
    private List<Wheel> wheelList;

    private Car(Builder builder) {
        setEngine(builder.engine);
        wheelList = builder.wheelList;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Car copy) {
        Builder builder = new Builder();
        builder.engine = copy.engine;
        builder.wheelList = copy.wheelList;
        return builder;
    }


    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }


    public List<Wheel> getWheelList() {
        return wheelList;
    }

    public void addWheel(Wheel wheel){
        this.getWheelList().add(wheel);
    }


    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", wheelList=" + wheelList +
                '}';
    }

    public static final class Builder {
        private Engine engine;
        private List<Wheel> wheelList;
        private WheelListBuilder wheelListBuilder = WheelListBuilder.newBuilder()
                .withNewList()
                .withCarBuilder(this);

        private Engine.Builder engineBuilder = Engine.newBuilder().withCarBuilder(this);


        private Builder() {
        }

        public WheelListBuilder addWheels(){
            return wheelListBuilder;
        }

        public Builder withEngine(Engine engine) {
            this.engine = engine;
            return this;
        }

        public Car build() {
            this.wheelList = wheelListBuilder.build();
            return new Car(this);
        }

        public Engine.Builder addEngine() {
            return engineBuilder;
        }
    }
}
