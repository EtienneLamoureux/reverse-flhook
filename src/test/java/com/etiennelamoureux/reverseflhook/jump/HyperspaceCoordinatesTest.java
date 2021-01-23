package com.etiennelamoureux.reverseflhook.jump;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HyperspaceCoordinatesTest {
  private static final String REAL_COORDS =
      "3BFEB408-AE10CA8E-8EB4E472-FEEE82A4-58F9E2F2-9C361755-34FDCC73";

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
