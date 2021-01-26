package com.etiennelamoureux.reverseflhook.exceptions;

public class NotEnoughSecretSegmentsException extends RuntimeException {
  private static final long serialVersionUID = -4423056380506257076L;

  public NotEnoughSecretSegmentsException(String currentSecret) {
    super("Current secret: " + currentSecret);
  }

}
