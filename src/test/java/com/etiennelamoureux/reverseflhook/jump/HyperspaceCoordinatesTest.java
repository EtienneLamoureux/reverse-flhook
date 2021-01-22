package com.etiennelamoureux.reverseflhook.jump;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HyperspaceCoordinatesTest {
  private static final String REAL_COORDS =
      "5f026735-30f79688-319b6b76-e2504022-22b927a1-02662068-35337726";

  @Test
  public void whenGettingStringThenGetCorrectString() {
    HyperspaceCoordinates hyperspaceCoordinates =
        new HyperspaceCoordinates(66125, new Coordinates(14315f, 165f, -2360f));
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
