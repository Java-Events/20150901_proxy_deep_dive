package org.rapidpm.event.opench2015.v003.checkedbuilder.v005;

import java.util.Optional;

/**
 * Created by sven on 30.04.15.
 */
public class DataHolder {

  public int a;
  public int b;
  public int c;

  private DataHolder(Builder builder) {
    this.a = builder.a;
    this.b = builder.b;
    this.c = builder.c;
  }

  public static Builder newBuilder() {
    return new Builder();
  }


  //extend from CheckedBuilder
  public static final class Builder extends CheckedBuilder<Builder, DataHolder> {
    private int a;
    private int b;
    private int c;

    private Builder() { }

    public Builder withA(int a) {
      this.a = a;
      return this;
    }
    public Builder withB(int b) {
      this.b = b;
      return this;
    }
    public Builder withC(int c) {
      this.c = c;
      return this;
    }

    //add manually - start
    public Optional<DataHolder> build() {
      final DataHolder dataHolder = new DataHolder(this);
      return checkAndGet(dataHolder);
    }
    //add manually - stop
  }


}
