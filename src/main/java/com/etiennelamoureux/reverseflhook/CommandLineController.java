package com.etiennelamoureux.reverseflhook;

import com.etiennelamoureux.reverseflhook.jump.HyperspaceCoordinates;
import com.etiennelamoureux.reverseflhook.jump.HyperspaceCoordinatesService;
import com.etiennelamoureux.reverseflhook.utils.KeyboardUtil;
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

  private Deque<String> stackArguments(String... args) {
    Deque<String> arguments = new LinkedList<>();
    Arrays.stream(args).forEach(n -> arguments.addLast(n));

    return arguments;
  }

  private void printHelp() {
    try (Stream<String> lines = new BufferedReader(new InputStreamReader(
        Thread.currentThread().getContextClassLoader().getResourceAsStream("help.txt"))).lines()) {
      lines.forEach(line -> System.out.println(line));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void run(Deque<String> arguments) throws InterruptedException {
    Command command = getCommand(arguments);
    HyperspaceCoordinates hyperspaceCoordinates;

    if (arguments.contains(Flags.ADMIN)) {
      hyperspaceCoordinatesService.isAdminMode();
    }

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

    System.out.println(hyperspaceCoordinates.toString());

    if (arguments.contains(Flags.COPY)) {
      copyToClipboard(hyperspaceCoordinates);
    }

    if (arguments.contains(Flags.AUTO)) {
      typeOnKeyboard(hyperspaceCoordinates);
    }
  }

  private Command getCommand(Deque<String> arguments) {
    return Command.valueOf(arguments.pop().toUpperCase(Locale.ROOT));
  }

  private void copyToClipboard(HyperspaceCoordinates hyperspaceCoordinates) {
    Toolkit.getDefaultToolkit().getSystemClipboard()
        .setContents(new StringSelection(hyperspaceCoordinates.toString()), null);

    System.out.println("Hyperspace coordinates copied to clipboard!");
  }

  private void typeOnKeyboard(HyperspaceCoordinates hyperspaceCoordinates)
      throws InterruptedException {
    System.out.println("Typing hyperspace coordinates in 5 seconds...");
    Thread.sleep(5000l);

    new KeyboardUtil().type(
        KeyboardUtil.ENTER + "/setcoords " + hyperspaceCoordinates.toString() + KeyboardUtil.ENTER);

    System.out.println("Done!");
  }

  enum Command {
    SURVEY, REFRESH
  }

  class Flags {
    static final String COPY = "-copy";
    static final String AUTO = "-auto";
    static final String ADMIN = "-admin";
  }
}
