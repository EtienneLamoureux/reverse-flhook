package com.etiennelamoureux.reverseflhook.jump;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.etiennelamoureux.reverseflhook.utils.Constants;
import com.etiennelamoureux.reverseflhook.utils.IdUtil;
import com.etiennelamoureux.reverseflhook.utils.TimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HyperspaceCoordinatesTest {
  private static final String REAL_COORDS =
      "5F026735-30F79688-319B6B76-E2504022-22B927A1-02662068-35337726";
  private static final int SYSTEM = IdUtil.generate("fp7_system");

  private Coordinates coordinates;

  @BeforeEach
  public void setUp() {
    initializeDataObjects();
  }

  @Test
  public void whenConstructingThenParseCorrectly() {
    HyperspaceCoordinates hyperspaceCoordinates = new HyperspaceCoordinates(SYSTEM, coordinates);
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

  @Test
  public void whenConstructingThenCoordsAreValidForAtLeast28Days() {
    HyperspaceCoordinates hyperspaceCoordinates = new HyperspaceCoordinates(SYSTEM, coordinates);

    int lifeTimeInDays =
        (hyperspaceCoordinates.getTime() - TimeUtil.secondsFrom1970()) / Constants.DAY_IN_SECONDS;

    assertTrue(lifeTimeInDays >= 28);
  }

  private void initializeDataObjects() {
    coordinates = new Coordinates(-30937, -3400, -13054);
  }

}
