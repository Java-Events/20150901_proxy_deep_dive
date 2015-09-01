package org.rapidpm.event.opench2015.v003.nestedbuilder.v004;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sven on 05.03.15.
 */
public class WheelListBuilder {

    private Car.Builder carBuilder;

    public static WheelListBuilder newBuilder(){
      return new WheelListBuilder();
    }

    private WheelListBuilder() {
    }

    private List<Wheel> wheelList;

    public WheelListBuilder withNewList(){
        this.wheelList = new ArrayList<>();
        return this;
    }
    public WheelListBuilder withList(List wheelList){
        this.wheelList = wheelList;
        return this;
    }

    public WheelListBuilder addWheel(Wheel wheel){
        this.wheelList.add(wheel);
        return this;
    }

    public Wheel.Builder addWheel(){
        Wheel.Builder builder = Wheel.newBuilder();
        builder.withWheelListBuilder(this);
        return builder;
    }


    public List<Wheel> build(){
        return this.wheelList;
    }


    public WheelListBuilder withCarBuilder(Car.Builder carBuilder){
        this.carBuilder = carBuilder;
        return this;
    }

    public Car.Builder done(){ return this.carBuilder;}


}
