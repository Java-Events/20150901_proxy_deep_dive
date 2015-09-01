package org.rapidpm.event.opench2015.v003.nestedbuilder.v002;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sven on 05.03.15.
 */
public class WheelListBuilder {

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

    public List<Wheel> build(){
        return this.wheelList;
    }

}
