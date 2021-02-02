package com.etiennelamoureux.reverseflhook.utils;

import com.etiennelamoureux.reverseflhook.exceptions.KeyboardException;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class KeyboardUtil {
  public static final String ENTER = "&";
  private static final long DELAY = 20l;

  private Robot robot;
  private Map<String, Integer> keyEventsByChar;

  public KeyboardUtil() {
    try {
      robot = new Robot();
    } catch (AWTException e) {
      throw new KeyboardException(e);
    }

    keyEventsByChar = new HashMap<>();
    keyEventsByChar.put("0", KeyEvent.VK_0);
    keyEventsByChar.put("1", KeyEvent.VK_1);
    keyEventsByChar.put("2", KeyEvent.VK_2);
    keyEventsByChar.put("3", KeyEvent.VK_3);
    keyEventsByChar.put("4", KeyEvent.VK_4);
    keyEventsByChar.put("5", KeyEvent.VK_5);
    keyEventsByChar.put("6", KeyEvent.VK_6);
    keyEventsByChar.put("7", KeyEvent.VK_7);
    keyEventsByChar.put("8", KeyEvent.VK_8);
    keyEventsByChar.put("9", KeyEvent.VK_9);
    keyEventsByChar.put("a", KeyEvent.VK_A);
    keyEventsByChar.put("b", KeyEvent.VK_B);
    keyEventsByChar.put("c", KeyEvent.VK_C);
    keyEventsByChar.put("d", KeyEvent.VK_D);
    keyEventsByChar.put("e", KeyEvent.VK_E);
    keyEventsByChar.put("f", KeyEvent.VK_F);
    keyEventsByChar.put("g", KeyEvent.VK_G);
    keyEventsByChar.put("h", KeyEvent.VK_H);
    keyEventsByChar.put("i", KeyEvent.VK_I);
    keyEventsByChar.put("j", KeyEvent.VK_J);
    keyEventsByChar.put("k", KeyEvent.VK_K);
    keyEventsByChar.put("l", KeyEvent.VK_L);
    keyEventsByChar.put("m", KeyEvent.VK_M);
    keyEventsByChar.put("n", KeyEvent.VK_N);
    keyEventsByChar.put("o", KeyEvent.VK_O);
    keyEventsByChar.put("p", KeyEvent.VK_P);
    keyEventsByChar.put("q", KeyEvent.VK_Q);
    keyEventsByChar.put("r", KeyEvent.VK_R);
    keyEventsByChar.put("s", KeyEvent.VK_S);
    keyEventsByChar.put("t", KeyEvent.VK_T);
    keyEventsByChar.put("u", KeyEvent.VK_U);
    keyEventsByChar.put("v", KeyEvent.VK_V);
    keyEventsByChar.put("w", KeyEvent.VK_W);
    keyEventsByChar.put("x", KeyEvent.VK_X);
    keyEventsByChar.put("y", KeyEvent.VK_Y);
    keyEventsByChar.put("z", KeyEvent.VK_Z);
    keyEventsByChar.put(" ", KeyEvent.VK_SPACE);
    keyEventsByChar.put("/", KeyEvent.VK_DIVIDE);
    keyEventsByChar.put("-", KeyEvent.VK_MINUS);
    keyEventsByChar.put(ENTER, KeyEvent.VK_ENTER);
  }

  public void type(String string) {
    for (int i = 0; i < string.length(); i++) {
      key(string.toLowerCase(Locale.ROOT).substring(i, i + 1));
    }
  }

  private void key(String key) {
    try {
      robot.keyPress(keyEventsByChar.get(key));
      Thread.sleep(DELAY);
      robot.keyRelease(keyEventsByChar.get(key));
      Thread.sleep(DELAY);
    } catch (InterruptedException e) {
      throw new KeyboardException(e);
    }
  }
}
