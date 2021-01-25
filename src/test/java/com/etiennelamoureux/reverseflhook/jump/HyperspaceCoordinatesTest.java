package com.etiennelamoureux.reverseflhook.jump;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HyperspaceCoordinatesTest {
  private static final String REAL_COORDS =
      "5F026735-30F79688-319B6B76-E2504022-22B927A1-02662068-35337726";

  @Test
  public void givenCircularUsageThenIsSelfConsistent() {
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
