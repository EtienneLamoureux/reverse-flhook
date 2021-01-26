package com.etiennelamoureux.reverseflhook.exceptions;

public class EntityNotFoundException extends RuntimeException {
  private static final long serialVersionUID = -2465314780613287951L;

  public EntityNotFoundException(String message) {
    super(message);
  }

}
