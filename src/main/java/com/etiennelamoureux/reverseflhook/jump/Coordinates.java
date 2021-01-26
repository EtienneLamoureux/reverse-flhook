package com.etiennelamoureux.reverseflhook.jump;

import java.util.List;

public class Coordinates {
  private static final float ERROR_MARGIN = 1000.0f;

  private float x;
  private float y;
  private float z;

  public Coordinates(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public Coordinates(List<Float> floats) {
    if (floats.size() != 3) {
      throw new IllegalArgumentException();
    }

    this.x = floats.get(0);
    this.y = floats.get(1);
    this.z = floats.get(2);
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public float getZ() {
    return z;
  }

  public Coordinates blur() {
    x += (Math.random() * ERROR_MARGIN) / 2;
    y += (Math.random() * ERROR_MARGIN) / 2;
    z += (Math.random() * ERROR_MARGIN) / 2;

    return this;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Float.floatToIntBits(x);
    result = prime * result + Float.floatToIntBits(y);
    result = prime * result + Float.floatToIntBits(z);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Coordinates other = (Coordinates) obj;
    if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
      return false;
    if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
      return false;
    if (Float.floatToIntBits(z) != Float.floatToIntBits(other.z))
      return false;
    return true;
  }
}
