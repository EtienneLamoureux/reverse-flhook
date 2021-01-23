package com.etiennelamoureux.reverseflhook.utils;

public class Cipher {
  public static final int KNOWN_LENGTH_IN_BYTES = 12;

  public String cipher;
  public float x;
  public float y;
  public float z;

  public Cipher(String cipher, float x, float y, float z) {
    this.cipher = cipher;
    this.x = x;
    this.y = y;
    this.z = z;
  }
}
