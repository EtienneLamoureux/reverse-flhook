package com.etiennelamoureux.reverseflhook.jump;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class CoordinatesRepositoryFTest {
  @Autowired
  private ApplicationContext applicationContext;

  @Test
  public void whenGettingCoordsThenFindCorrectOnes() throws Exception {
    CoordinatesRepository coordinatesRepository =
        applicationContext.getBean(CoordinatesRepository.class);

    Coordinates coordinates = coordinatesRepository.findOneBySystemAndBase("ew01", "ew01_01_base");

    assertEquals(new Coordinates(14360, 0, -2251), coordinates);
  }
}
