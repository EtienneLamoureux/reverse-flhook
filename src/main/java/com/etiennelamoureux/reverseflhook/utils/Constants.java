package com.etiennelamoureux.reverseflhook.utils;

public class Constants {
  public static final float SURVEY_MK3_ACCURACY = 25000;
  public static final int DAY_IN_SECONDS = 86400;
  public static final int HYPERSPACE_COORDINATES_LIFETIME = 35 * DAY_IN_SECONDS;

  /**
   * The secret actually has a 0-byte at the end, as the loop has an off-by-one error.
   * 
   * @see <a href=
   *      "https://github.com/DiscoveryGC/FLHook/blob/9499f8be19611b836afcf292938da78033b9acef/Plugins/Public/playercntl_plugin/HyperJump.cpp#L186">Bug
   *      detail</a> (Should be >=, not simply >)
   */
  public static final byte[] SECRET_PHRASE = "secretcode".getBytes();
  public static final byte[] SECRET = new byte[SECRET_PHRASE.length + 1];
  static {
    for (int i = 0; i < SECRET_PHRASE.length; i++) {
      SECRET[i] = SECRET_PHRASE[i];
    }
  }

}
