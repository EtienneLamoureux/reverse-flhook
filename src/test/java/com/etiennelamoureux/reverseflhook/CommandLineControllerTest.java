package com.etiennelamoureux.reverseflhook;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.etiennelamoureux.reverseflhook.jump.HyperspaceCoordinatesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CommandLineControllerTest {
  @Mock
  private HyperspaceCoordinatesService hyperspaceCoordinatesService;

  @InjectMocks
  private CommandLineController commandLineController;

  @Test
  public void givenSystemWhenSurveyingThenCallCorrectMethod() throws Exception {
    commandLineController.run("survey", "ew01", "ew01_01_base");

    verify(hyperspaceCoordinatesService, times(1)).survey(anyString(), anyString());
  }

  @Test
  public void givenPositionWhenSurveyingThenCallCorrectMethod() throws Exception {
    commandLineController.run("survey", "fp7_system", "-30937", "-3400", "-13054");

    verify(hyperspaceCoordinatesService, times(1)).survey(any(), anyFloat(), anyFloat(),
        anyFloat());
  }

  @Test
  public void whenRefreshingThenCallCorrectMethod() throws Exception {
    commandLineController.run("refresh",
        "16FE3679-B69CDBB3-341841A5-C6AD29C4-DAFAA176-FD8A5201-2DFD9B72");

    verify(hyperspaceCoordinatesService, times(1)).refresh(anyString());
  }
}
