package com.etiennelamoureux.reverseflhook.utils;

public class HexUtil {
  public static String toHexString(byte singleByte) {
    return String.format("%02X", singleByte);
  }

  /**
   * @see <a href=
   *      "https://stackoverflow.com/questions/140131/convert-a-string-representation-of-a-hex-dump-to-a-byte-array-using-java">Reference</a>
   * @param string Must be even-length
   * @return {@link byte}
   */
  public static byte[] toByteArray(String string) {
    int length = string.length();

    if (length % 2 != 0) {
      throw new IllegalArgumentException(String.format("String '%s' must be even-length", string));
    }

    byte[] data = new byte[length / 2];

    for (int i = 0; i < length; i += 2) {
      data[i / 2] = (byte) ((Character.digit(string.charAt(i), 16) << 4)
          + Character.digit(string.charAt(i + 1), 16));
    }

    return data;
  }
}
