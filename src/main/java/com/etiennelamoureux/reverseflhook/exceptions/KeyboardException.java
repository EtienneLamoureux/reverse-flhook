package com.etiennelamoureux.reverseflhook.exceptions;

public class KeyboardException extends RuntimeException {
  private static final long serialVersionUID = 1422275621352262759L;

  public KeyboardException(Exception e) {
    super(e);
  }
}
