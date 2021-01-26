package com.etiennelamoureux.reverseflhook.utils;

import com.etiennelamoureux.reverseflhook.exceptions.NotEnoughSecretSegmentsException;
import org.junit.jupiter.api.Test;

public class SecretBreakerTest {
  @Test
  public void whenRunningMainThenBreakSecret() {
    try {
      SecretBreaker.main(new String[] {});
    } catch (NotEnoughSecretSegmentsException e) {
      // expected
    }
  }
}
