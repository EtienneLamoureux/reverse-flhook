package com.etiennelamoureux.reverseflhook;

import com.etiennelamoureux.reverseflhook.jump.HyperspaceCoordinates;
import com.etiennelamoureux.reverseflhook.jump.HyperspaceCoordinatesService;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class CommandLineController implements CommandLineRunner {
  @Autowired
  private HyperspaceCoordinatesService hyperspaceCoordinatesService;

  @Override
  public void run(String... args) throws Exception {
    Deque<String> arguments = stackArguments(args);
    Command command = Command.valueOf(arguments.pop().toUpperCase(Locale.ROOT));
    HyperspaceCoordinates hyperspaceCoordinates;

    switch (command) {
      case SURVEY: {
        hyperspaceCoordinates =
            hyperspaceCoordinatesService.survey(arguments.pop(), arguments.pop());
        break;
      }
      default:
      case REFRESH: {
        hyperspaceCoordinates = hyperspaceCoordinatesService.refresh(arguments.pop());
        break;
      }
    }

    System.out.println(hyperspaceCoordinates.toString());
  }

  private Deque<String> stackArguments(String... args) {
    Deque<String> arguments = new LinkedList<>();
    Arrays.stream(args).forEach(n -> arguments.addLast(n));

    return arguments;
  }

  enum Command {
    SURVEY, REFRESH
  }
}
