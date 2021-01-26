package com.etiennelamoureux.reverseflhook.utils;

import java.nio.charset.Charset;

/**
 * @see <a href=
 *      "https://github.com/DiscoveryGC/FLHook/blob/3817c575c9584fd3c7f572af9d2c0ac0f4bf62eb/Plugins/Public/playercntl_plugin/setup_src/FLUtility.cs#L111">Original
 *      algorithm</a>
 */
public class IdUtil {
  private static final int FLHASH_POLYNOMIAL = 0xA001;
  private static final int LOGICAL_BITS = 30;
  private static final int PHYSICAL_BITS = 32;
  private static final int[] HASHING_ARRAY = new int[256];
  static {
    for (int i = 0; i < 256; i++) {
      int x = i;

      for (int bit = 0; bit < 8; bit++) {
        x = ((x & 1) == 1) ? (x >> 1) ^ (FLHASH_POLYNOMIAL << (LOGICAL_BITS - 16)) : x >> 1;
      }

      HASHING_ARRAY[i] = x;
    }
  };

  public static int generate(String string) {
    int id = 0;
    byte[] bytes = string.toLowerCase().getBytes(Charset.forName("ASCII"));

    for (int i = 0; i < string.length(); i++) {
      id = (id >> 8) ^ HASHING_ARRAY[Byte.toUnsignedInt((byte) (id ^ bytes[i]))];
    }

    id = (id >> 24) | ((id >> 8) & 0x0000FF00) | ((id << 8) & 0x00FF0000) | (id << 24);
    id = (id >>> (PHYSICAL_BITS - LOGICAL_BITS)) | 0x80000000;

    return id;
  }
}
