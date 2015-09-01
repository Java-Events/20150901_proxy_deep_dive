package org.rapidpm.event.opench2015.v003.nestedbuilder.v006;


/**
 * Created by sven on 05.03.15.
 */
public class KidB {

  private String note;

  @Override
  public String toString() {
    return "KidB{" +
        "note='" + note + '\'' +
        '}';
  }

  private KidB(Builder builder) {
    note = builder.note;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static final class Builder extends NestedBuilder<KidA.Builder, KidB> {
    private String note;

    private Builder() {
    }

    public Builder withNote(String note) {
      this.note = note;
      return this;
    }

    public KidB build() {
      return new KidB(this);
    }

  }
}
