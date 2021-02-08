package com.etiennelamoureux.reverseflhook.jump;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HyperspaceCoordinatesServiceTest {
  private static final String SYSTEM = "system";
  private static final String BASE = "base";
  private static final float X = 1;
  private static final float Y = 2;
  private static final float Z = 3;
  private static final Coordinates COORDS = new Coordinates(X, Y, Z);

  @Mock
  private CoordinatesRepository coordinatesRepository;

  @InjectMocks
  private HyperspaceCoordinatesService hyperspaceCoordinatesService;

  @Test
  public void givenNotAdminWhenSurveyingCoordsThenBlur() {
    HyperspaceCoordinates hyperspaceCoordinates =
        hyperspaceCoordinatesService.survey(SYSTEM, X, Y, Z);

    assertNotEquals(COORDS, hyperspaceCoordinates.getCoordinates());
  }

  @Test
  public void givenNotAdminWhenSurveyingBaseThenBlur() {
    when(coordinatesRepository.findOneBySystemAndBase(SYSTEM, BASE))
        .thenReturn(new Coordinates(X, Y, Z));

    HyperspaceCoordinates hyperspaceCoordinates = hyperspaceCoordinatesService.survey(SYSTEM, BASE);

    assertNotEquals(COORDS, hyperspaceCoordinates.getCoordinates());
  }

  @Test
  public void givenAdminWhenSurveyingCoordsThenDontBlur() {
    hyperspaceCoordinatesService.isAdminMode();

    HyperspaceCoordinates hyperspaceCoordinates =
        hyperspaceCoordinatesService.survey(SYSTEM, X, Y, Z);

    assertEquals(COORDS, hyperspaceCoordinates.getCoordinates());
  }

  @Test
  public void givenAdminWhenSurveyingBaseThenDontBlur() {
    when(coordinatesRepository.findOneBySystemAndBase(SYSTEM, BASE))
        .thenReturn(new Coordinates(X, Y, Z));
    hyperspaceCoordinatesService.isAdminMode();

    HyperspaceCoordinates hyperspaceCoordinates = hyperspaceCoordinatesService.survey(SYSTEM, BASE);

    assertEquals(COORDS, hyperspaceCoordinates.getCoordinates());
  }
}
