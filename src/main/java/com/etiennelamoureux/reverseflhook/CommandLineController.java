package com.etiennelamoureux.reverseflhook;

import com.etiennelamoureux.reverseflhook.jump.HyperspaceCoordinates;
import com.etiennelamoureux.reverseflhook.jump.HyperspaceCoordinatesService;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Locale;
import java.util.stream.Stream;
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

    try {
      run(arguments);
    } catch (Exception e) {
      System.out.println("ERROR: " + e.getMessage());
      printHelp();
    }
  }

  private void run(Deque<String> arguments) {
    Command command = getCommand(arguments);
    HyperspaceCoordinates hyperspaceCoordinates;

    switch (command) {
      case SURVEY: {
        String system = arguments.pop();

        try {
          Float.parseFloat(arguments.peek());
          hyperspaceCoordinates =
              hyperspaceCoordinatesService.survey(system, Float.parseFloat(arguments.pop()),
                  Float.parseFloat(arguments.pop()), Float.parseFloat(arguments.pop()));
        } catch (NumberFormatException e) {
          hyperspaceCoordinates = hyperspaceCoordinatesService.survey(system, arguments.pop());
        }
        break;
      }
      default:
      case REFRESH: {
        hyperspaceCoordinates = hyperspaceCoordinatesService.refresh(arguments.pop());
        break;
      }
    }

    if (arguments.contains(Flags.COPY)) {
      Toolkit.getDefaultToolkit().getSystemClipboard()
          .setContents(new StringSelection(hyperspaceCoordinates.toString()), null);
    }

    if (arguments.contains(Flags.AUTO)) {
      // TODO
    }

    System.out.println(hyperspaceCoordinates.toString());
  }

  private Deque<String> stackArguments(String... args) {
    Deque<String> arguments = new LinkedList<>();
    Arrays.stream(args).forEach(n -> arguments.addLast(n));

    return arguments;
  }

  private Command getCommand(Deque<String> arguments) {
    return Command.valueOf(arguments.pop().toUpperCase(Locale.ROOT));
  }

  private void printHelp() {
    try (Stream<String> lines = new BufferedReader(new InputStreamReader(
        Thread.currentThread().getContextClassLoader().getResourceAsStream("help.txt"))).lines()) {
      lines.forEach(line -> System.out.println(line));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  enum Command {
    SURVEY, REFRESH
  }

  class Flags {
    static final String COPY = "-copy";
    static final String AUTO = "-auto";
  }
}
