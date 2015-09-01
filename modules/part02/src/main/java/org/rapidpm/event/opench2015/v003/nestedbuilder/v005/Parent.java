package org.rapidpm.event.opench2015.v003.nestedbuilder.v005;

/**
 * Created by sven on 05.03.15.
 */
public class Parent {

  private KidA kidA;
  private KidB kidB;


  @Override
  public String toString() {
    return "Parent{" +
        "kidA=" + kidA +
        ", kidB=" + kidB +
        '}';
  }

  private Parent(Builder builder) {
    kidA = builder.kidA;
    kidB = builder.kidB;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static final class Builder {
    private KidA kidA;
    private KidB kidB;

    private Builder() {
    }

    public Builder withKidA(KidA kidA) {
      this.kidA = kidA;
      return this;
    }

    public Builder withKidB(KidB kidB) {
      this.kidB = kidB;
      return this;
    }

    // to add manually
    private KidA.Builder builderKidA = KidA.newBuilder().withParentBuilder(this);
    private KidB.Builder builderKidB = KidB.newBuilder().withParentBuilder(this);

    public KidA.Builder addKidA() {
      return this.builderKidA;
    }

    public KidB.Builder addKidB() {
      return this.builderKidB;
    }
    //---------


    public Parent build() {
      return new Parent(this);
    }
  }
}
