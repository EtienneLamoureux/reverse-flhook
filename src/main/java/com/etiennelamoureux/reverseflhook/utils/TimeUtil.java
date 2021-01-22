package com.etiennelamoureux.reverseflhook.utils;

import java.sql.Timestamp;
import java.time.Instant;

public class TimeUtil {
  public static int secondsFrom1970() {
    return (int) (Timestamp.from(Instant.now()).getTime() / 1000l);
  }

  public static int upTo1WeekInSeconds() {
    return (int) (Math.random() * (Constants.DAY_IN_SECONDS * 7));
  }
}
