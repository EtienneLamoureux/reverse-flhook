package com.etiennelamoureux.reverseflhook.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class IdUtilTest {
  @Test
  public void givenKnownNicknamesWhenConvertingThenGetKnownIds() {
    assertEquals(-11193, IdUtil.generate("st01_to_st03_hole"));
    assertEquals(27714, IdUtil.generate("st02_to_st01_hole"));
    assertEquals(17474, IdUtil.generate("st03_to_st01_hole"));
    assertEquals(21761, IdUtil.generate("li05_to_li01"));
    assertEquals(-16127, IdUtil.generate("li01_to_li05"));
  }
}
