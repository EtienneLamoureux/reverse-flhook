package com.etiennelamoureux.reverseflhook;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class CommandLineControllerITest {
  @Autowired
  private ApplicationContext applicationContext;

  @Test
  public void whenSurveyingBaseThenDontThrow() throws Exception {
    CommandLineController commandLineController =
        applicationContext.getBean(CommandLineController.class);
    commandLineController.run("survey", "ew01", "ew01_01_base");
  }

  @Test
  public void whenSurveyingPositionThenDontThrow() throws Exception {
    CommandLineController commandLineController =
        applicationContext.getBean(CommandLineController.class);
    commandLineController.run("survey", "fp7_system", "-30937", "-3400", "-13054");
  }

  @Test
  public void whenRefreshingThenDontThrow() throws Exception {
    CommandLineController commandLineController =
        applicationContext.getBean(CommandLineController.class);
    commandLineController.run("refresh",
        "16FE3679-B69CDBB3-341841A5-C6AD29C4-DAFAA176-FD8A5201-2DFD9B72");
  }
}
