package com.etiennelamoureux.reverseflhook;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ReverseFLHook {

  public static void main(String[] args) {
    new SpringApplicationBuilder(ReverseFLHook.class).headless(false).run(args);
  }

}
