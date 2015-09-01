package org.rapidpm.event.opench2015.v003.nestedbuilder.v006;


/**
 * Created by sven on 05.03.15.
 */
public class Parent {

  private KidA kidA;

  @Override
  public String toString() {
    return "Parent{" +
        "kidA=" + kidA +
        '}';
  }

  private Parent(Builder builder) {
    kidA = builder.kidA;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static final class Builder {
    private KidA kidA;

    private Builder() {
    }

    public Builder withKidA(KidA kidA) {
      this.kidA = kidA;
      return this;
    }

    // to add manually
    private KidA.Builder builderKidA = KidA.newBuilder().withParentBuilder(this);
    public KidA.Builder addKidA() {
      return this.builderKidA;
    }
    //---------

    public Parent build() {
      return new Parent(this);
    }
  }
}
