package com.etiennelamoureux.reverseflhook.utils;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class SecretBreaker {
  /**
   * Given that the XYZ coordinates are sequential in the cipher, and their value before and after
   * encryption are known, it's possible to break the part of the symmetric key used on them. Since
   * the key shifts by and unknown amount of bytes from cipher to cipher, it's possible to break the
   * whole key given enough ciphers.
   * 
   * @param args
   */
  public static void main(String[] args) {
    Arrays.asList(
        new Cipher("5f026735-30f79688-319b6b76-e2504022-22b927a1-02662068-35337726", 14315f, 165f,
            -2360f),
        new Cipher("ac003a2f-32d4aeea-b93b4ef3-46da3e74-ec587c73-d6b0e573-33697072", -62988f, 9f,
            -40814f),
        new Cipher("cc004a33-6d9a9f8b-04973a26-4dcb9b5b-df8118f2-0d84e43f-61656e74", 57263f, 1f,
            -11115f),
        new Cipher("14033e66-3d619f83-72d116fe-1b094c76-1a3ee920-96c3e86a-64316e72", -41957f, 63f,
            6696f),
        new Cipher("bd005b7f-3b0398a2-93ac6474-dc9d7277-8c822b25-21c6e05e-38352277", 55194f, 224f,
            9775f),
        new Cipher("46027c62-67568797-f000f9f3-6eb03a72-6300ef6b-4813f36b-3363777f", -27185f,
            13921f, 27953f),
        new Cipher("47001067-36928ae7-d90ebef2-dddb6823-2dbafc75-3069d66c-33647b6a", -17810f, 55f,
            26824f),
        new Cipher("c2fe530d-b617cc83-b7f8f173-77822ff4-e55ec2fe-e7c20558-34acce6a", 25323f, -819f,
            -24887f),
        new Cipher("1ffff36f-b768ffaf-e0ad567d-7b4bf8ec-a44a83a7-6ad15755-2dfb9674", 3693f, -19f,
            -1460f))
        .stream().forEach(n -> breakCipher(n));
  }

  private static void breakCipher(Cipher cipher) {
    byte[] encrypted = HexUtil.toByteArray(cipher.cipher.replace("-", "").substring(16, 40));
    byte[] decrypted = ByteBuffer.allocate(Cipher.KNOWN_LENGTH_IN_BYTES).putFloat(cipher.x)
        .putFloat(cipher.y).putFloat(cipher.z).array();
    byte[] secret = new byte[Cipher.KNOWN_LENGTH_IN_BYTES];

    for (int i = 0; i < encrypted.length; i++) {
      secret[i] = (byte) (encrypted[i] ^ decrypted[i]);
    }

    System.out.println(Arrays.toString(secret));
  }

}
