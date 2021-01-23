package com.etiennelamoureux.reverseflhook.jump;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HyperspaceCoordinatesTest {
  private static final String REAL_COORDS =
      "d5ffd205-7eeae6ae-1817e425-c660c976-dfe23276-7eef0454-38e59b72";

  @Test
  public void whenGettingStringThenGetCorrectString() {
    HyperspaceCoordinates hyperspaceCoordinates =
        new HyperspaceCoordinates(100, new Coordinates(1, 2, 3));
    String hyperspaceCoordinatesString = hyperspaceCoordinates.toString();

    HyperspaceCoordinates parsedHyperspaceCoordinates =
        new HyperspaceCoordinates(hyperspaceCoordinatesString);

    assertEquals(hyperspaceCoordinates, parsedHyperspaceCoordinates);
  }

  @Test
  public void givenRealHyperspaceCoordinatesWhenConstructingThenParseCorrectly() {
    HyperspaceCoordinates hyperspaceCoordinates = new HyperspaceCoordinates(REAL_COORDS);

    assertEquals(REAL_COORDS, hyperspaceCoordinates.toString());
  }
}
