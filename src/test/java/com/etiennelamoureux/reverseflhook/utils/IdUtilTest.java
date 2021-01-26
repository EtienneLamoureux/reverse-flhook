package com.etiennelamoureux.reverseflhook.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class IdUtilTest {
  @Test
  public void givenOriginalExamplesWhenConvertingThenGetKnownIds() {
    assertEquals(2926433351l, Integer.toUnsignedLong(IdUtil.generate("st01_to_st03_hole")));
    assertEquals(2460445762l, Integer.toUnsignedLong(IdUtil.generate("st02_to_st01_hole")));
    assertEquals(2263303234l, Integer.toUnsignedLong(IdUtil.generate("st03_to_st01_hole")));
    assertEquals(2284213505l, Integer.toUnsignedLong(IdUtil.generate("li05_to_li01")));
    assertEquals(2293678337l, Integer.toUnsignedLong(IdUtil.generate("li01_to_li05")));
  }

  @Test
  public void givenTau37WhenConvertingThenGetKnownId() {
    assertEquals(-1146109436, IdUtil.generate("ew01"));
  }
}
