package org.rapidpm.event.opench2015.v003.nestedbuilder.v006;


/**
 * Created by sven on 05.03.15.
 */
public class KidA {

  private KidB kidB;
  private String note;

  @Override
  public String toString() {
    return "KidA{" +
        "kidB=" + kidB +
        ", note='" + note + '\'' +
        '}';
  }

  private KidA(Builder builder) {
    kidB = builder.kidB;
    note = builder.note;
  }

  public static Builder newBuilder() {
    return new Builder();
  }


  public static final class Builder extends NestedBuilder<Parent.Builder, KidA> {
    private KidB kidB;
    private String note;

    private Builder() {
    }

    public Builder withKidB(KidB kidB) {
      this.kidB = kidB;
      return this;
    }

    public Builder withNote(String note) {
      this.note = note;
      return this;
    }

    // to add manually
    private KidB.Builder builderKidB = KidB.newBuilder().withParentBuilder(this);

    public KidB.Builder addKidB() {
      return this.builderKidB;
    }
    //---------


    public KidA build() {
      return new KidA(this);
    }

  }
}
