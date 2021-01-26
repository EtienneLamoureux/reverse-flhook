package com.etiennelamoureux.reverseflhook;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class CommandLineController implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {
    System.out.println("bonjour");
  }

}
