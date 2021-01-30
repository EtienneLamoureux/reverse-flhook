package com.etiennelamoureux.reverseflhook.utils;

import org.springframework.beans.factory.annotation.Value;

public class Secret {
  private static final String SECRET = "8ea084da-545c4aa5-39353348-4593c040";

  private static Secret instance;

  /**
   * The secret actually has a 0-byte at the end, as the loop has an off-by-one error.
   * 
   * @see <a href=
   *      "https://github.com/DiscoveryGC/FLHook/blob/9499f8be19611b836afcf292938da78033b9acef/Plugins/Public/playercntl_plugin/HyperJump.cpp#L186">Bug
   *      detail</a> (Should be >=, not simply >)
   */
  @Value(value = "${flhook.secret}")
  private String secret;
  private byte[] bytes;

  private Secret() {
    byte[] secret = ((this.secret == null) ? SECRET : this.secret).getBytes();
    bytes = new byte[secret.length + 1];

    for (int i = 0; i < secret.length; i++) {
      bytes[i] = secret[i];
    }
  }

  public byte[] getBytes() {
    return bytes;
  }

  public static Secret getInstance() {
    if (instance == null) {
      instance = new Secret();
    }

    return instance;
  }
}
