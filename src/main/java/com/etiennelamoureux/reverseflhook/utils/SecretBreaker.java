package com.etiennelamoureux.reverseflhook.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
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
        new Cipher("5f026735-30f79688-319b6b76-e2504022-22b927a1-02662068-35337726", 14315, 165,
            -2360),
        new Cipher("ac003a2f-32d4aeea-b93b4ef3-46da3e74-ec587c73-d6b0e573-33697072", -62988, 9,
            -40814),
        new Cipher("cc004a33-6d9a9f8b-04973a26-4dcb9b5b-df8118f2-0d84e43f-61656e74", 57263, 1,
            -11115),
        new Cipher("14033e66-3d619f83-72d116fe-1b094c76-1a3ee920-96c3e86a-64316e72", -41957, 63,
            6696),
        new Cipher("bd005b7f-3b0398a2-93ac6474-dc9d7277-8c822b25-21c6e05e-38352277", 55194, 224,
            9775),
        new Cipher("46027c62-67568797-f000f9f3-6eb03a72-6300ef6b-4813f36b-3363777f", -27185, 13921,
            27953),
        new Cipher("47001067-36928ae7-d90ebef2-dddb6823-2dbafc75-3069d66c-33647b6a", -17810, 55,
            26824),
        new Cipher("c2fe530d-b617cc83-b7f8f173-77822ff4-e55ec2fe-e7c20558-34acce6a", -31108, -3,
            -16599),
        new Cipher("d700cf04-29ef5e8f-9e46b427-11af3971-8f6f6576-ac880e4d-34fd9674", 16403, 37,
            3334),
        new Cipher("16fe3679-b69cdbb3-341841a5-c6ad29c4-dafaa176-fd8a5201-2dfd9b72", -15496, -614,
            24656),
        new Cipher("d1fe737b-3a37f68b-8107c725-d984f70a-f7f2aca7-c4ec0559-33fd9c74", 1306, 2,
            -26339),
        new Cipher("8400d717-baef9c87-ea233c7e-ea2e41fb-2b6a336b-c0e20203-34a9ce72", 12037, -241,
            13444),
        new Cipher("78ff5323-298d96a8-314f5b71-a1bf11f1-c4d381f5-04ea3758-65a99f7f", 3720, -662,
            -7260),
        new Cipher("d5ffd205-7eeae6ae-1817e425-c660c976-dfe23276-7eef0454-38e59b72", 26770, 336,
            2237),
        new Cipher("30ff0a12-2bafc290-0b34c6f5-46e966ed-8c9bb8f5-97820250-00f0ca26", 25323, -819,
            -24887),
        new Cipher("3bfeb408-ae10ca8e-8eb4e472-feee82a4-58f9e2f2-9c361755-34fdcc73", 27244, -23,
            -28005),
        new Cipher("26ff9d44-b7409e9a-88e3e125-fa8fdbf4-14fae3f5-5c6b0954-38e59b72", 27244, -23,
            -28005),
        new Cipher("1ffff36f-b768ffaf-e0ad567d-7b4bf8ec-a44a83a7-6ad15755-2dfb9674", 3693, -19,
            -1460))
        .stream().forEach(n -> breakCipher(n));
  }

  private static void breakCipher(Cipher cipher) {
    byte[] encrypted = HexUtil.toByteArray(cipher.cipher.replace("-", "").substring(16, 40));
    byte[] decrypted =
        ByteBuffer.allocate(Cipher.KNOWN_LENGTH_IN_BYTES).order(ByteOrder.LITTLE_ENDIAN)
            .putFloat(cipher.x).putFloat(cipher.y).putFloat(cipher.z).array();
    byte[] secret = new byte[Cipher.KNOWN_LENGTH_IN_BYTES];

    for (int i = 0; i < encrypted.length; i++) {
      secret[i] = (byte) (encrypted[i] ^ decrypted[i]);
    }

    System.out.println(Arrays.toString(secret));
    // System.out.println(new String(secret));
  }

}
