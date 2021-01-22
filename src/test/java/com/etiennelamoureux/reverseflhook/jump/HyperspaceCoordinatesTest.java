package com.etiennelamoureux.reverseflhook.jump;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HyperspaceCoordinatesTest {
  @Test
  public void test() {
    HyperspaceCoordinates hyperspaceCoordinates =
        new HyperspaceCoordinates(66125, new Coordinates(14315f, 165f, -2360f));
    String hyperspaceCoordinatesString = hyperspaceCoordinates.toString();
    System.out.println(hyperspaceCoordinatesString);

    HyperspaceCoordinates parsedHyperspaceCoordinates =
        new HyperspaceCoordinates(hyperspaceCoordinatesString);

    assertEquals(hyperspaceCoordinates, parsedHyperspaceCoordinates);
  }
}
