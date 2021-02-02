package com.etiennelamoureux.reverseflhook;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class CommandLineControllerITest {
  @Autowired
  private ApplicationContext applicationContext;

  private CommandLineController commandLineController;

  @BeforeAll
  public static void beforeAll() {
    System.setProperty("java.awt.headless", "false");
  }

  @BeforeEach
  public void setUp() {
    commandLineController = applicationContext.getBean(CommandLineController.class);
  }

  @Test
  public void givenCopyFlagWhenSurveyingThenCoordsAreInClipboard() throws Exception {
    commandLineController.run("survey", "ew01", "ew01_01_base", CommandLineController.Flags.COPY);

    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    String hyperspaceCoordinates = (String) clipboard.getData(DataFlavor.stringFlavor);
    commandLineController.run("refresh", hyperspaceCoordinates);
  }

  @Disabled(value = "Actually sends key-press events")
  @Test
  public void givenAutoFlagWhenSurveyingThenDontThrow() throws Exception {
    commandLineController.run("survey", "ew01", "ew01_01_base", CommandLineController.Flags.AUTO);
  }
}
