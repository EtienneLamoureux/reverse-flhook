package com.etiennelamoureux.reverseflhook;

import com.etiennelamoureux.reverseflhook.jump.HyperspaceCoordinatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class CommandLineController implements CommandLineRunner {
  @Autowired
  private HyperspaceCoordinatesService hyperspaceCoordinatesService;

  @Override
  public void run(String... args) throws Exception {
    System.out.println("bonjour");
  }

  enum Commands {
    SURVEY, REFRESH
  }
}
