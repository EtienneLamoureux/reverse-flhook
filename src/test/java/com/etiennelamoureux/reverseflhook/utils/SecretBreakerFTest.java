package com.etiennelamoureux.reverseflhook.utils;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class SecretBreakerFTest {
  @Disabled(
      value = "Might fail from time to time as the actual secret does have repeating segments of {@link SecretBreaker#OVERLAP_LENGTH} length")
  @Test
  public void whenRunningMainThenBreakSecret() {
    SecretBreaker.main(new String[] {});
  }
}
