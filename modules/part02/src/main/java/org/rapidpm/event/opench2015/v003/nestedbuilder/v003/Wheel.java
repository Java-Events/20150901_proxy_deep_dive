package org.rapidpm.event.opench2015.v003.nestedbuilder.v003;

/**
 * Created by sven on 05.03.15.
 */
public class Wheel {

    private int size;
    private int type;
    private int colour;

    private Wheel(Builder builder) {
        setSize(builder.size);
        setType(builder.type);
        setColour(builder.colour);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Wheel copy) {
        Builder builder = new Builder();
        builder.size = copy.size;
        builder.type = copy.type;
        builder.colour = copy.colour;
        return builder;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }


    @Override
    public String toString() {
        return "Wheel{" +
                "colour=" + colour +
                ", size=" + size +
                ", type=" + type +
                '}';
    }


    public static final class Builder {
        private int size;
        private int type;
        private int colour;

        private WheelListBuilder wheelListBuilder;


        private Builder() {
        }


        protected Builder withWheelListBuilder(WheelListBuilder wheelListBuilder){
            this.wheelListBuilder = wheelListBuilder;
            return this;
        }

        public Builder withSize(int size) {
            this.size = size;
            return this;
        }

        public Builder withType(int type) {
            this.type = type;
            return this;
        }

        public Builder withColour(int colour) {
            this.colour = colour;
            return this;
        }

        public Wheel build() {
            return new Wheel(this);
        }

        public WheelListBuilder addWheelToList(){
            this.wheelListBuilder.addWheel(this.build());
            return this.wheelListBuilder;
        }

    }
}
