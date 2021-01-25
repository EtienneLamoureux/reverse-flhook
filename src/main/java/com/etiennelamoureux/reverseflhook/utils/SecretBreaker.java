package com.etiennelamoureux.reverseflhook.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class SecretBreaker {
  private static final int KNOWN_LENGTH_IN_BYTES = 4;
  private static final int OVERLAP_LENGTH = KNOWN_LENGTH_IN_BYTES / 2;

  /**
   * Given that the accuracy is always at the end in the cipher, and that its value before and after
   * encryption is known, it's possible to break the part of the symmetric key used for it. Since
   * the key shifts by and unknown amount of bytes from cipher to cipher, it's possible to break the
   * whole key given enough ciphers.
   * 
   * @param args
   */
  public static void main(String[] args) {
    Collection<String> ciphers =
        Arrays.asList("1800614D-AE10CA8E-7DC8E272-CBBDD8A5-3852E3F2-5FF91655-34FDCC73",
            "A9FE6727-E014CB87-5093B727-E2BF9FA4-0A4FEEF2-91FB0F01-61FD8274",
            "FCFE8374-B610CAD4-6EA6B373-80AF88F2-D71FE8F2-4EDC0855-39FBCC77",
            "D0FFF034-831C9AD6-7793E622-6D2625F4-7D05EFA7-18AC1653-39FB9A74",
            "D1FF0519-BB419E87-7F9FB627-213E24F5-2B52BAA7-C1B40859-33FD9C74",
            "E8FF0C24-B71CD283-7292E125-613D20C0-7003BAF6-87AE5F01-2DFD9B72",
            "64032E1C-7F2A9783-1D3D67F7-417F2722-4751F573-15C95601-35E59C7E",
            "D5FFD205-7EEAE6AE-1817E425-C660C976-DFE23276-7EEF0454-38E59B72",
            "78FF5323-298D96A8-314F5B71-A1BF11F1-C4D381F5-04EA3758-65A99F7F",
            "A6FF542A-3BFB98D8-C6CDA846-7F8A40F5-CAC832A4-97ED0355-63FCCE26",
            "1FFFF36F-B768FFAF-E0AD567D-7B4BF8EC-A44A83A7-6AD15755-2DFB9674",
            "1000585D-F176F5A1-FC1EC5FE-482AB1FC-75027DF2-AD9F0D05-61F89773",
            "8400D717-BAEF9C87-EA233C7E-EA2E41FB-2B6A336B-C0E20203-34A9CE72",
            "D700CF04-29EF5E8F-9E46B427-11AF3971-8F6F6576-AC880E4D-34FD9674",
            "30FF0A12-2BAFC290-0B34C6F5-46E966ED-8C9BB8F5-97820250-00F0CA26",
            "D1FE737B-3A37F68B-8107C725-D984F70A-F7F2ACA7-C4EC0559-33FD9C74",
            "16FE3679-B69CDBB3-341841A5-C6AD29C4-DAFAA176-FD8A5201-2DFD9B72",
            "5B00201C-B010C79A-A48DEB75-196C9BF1-5808BEA7-78710804-61E59A73",
            "16FEAC1F-B71CD283-6FFEE125-9DA881C1-DA49BAF6-38C55801-2DFD9B72",
            "24004906-B31CCBD3-F195E772-4F3F9BA0-3905F6F5-FB7A0953-33FC976A",
            "9C00056E-B01DCC82-A38BE67E-57689AF8-6B53EBF2-694A0405-61F89773",
            "BBFFDC63-E245CA9A-A381E173-496F9BF9-7504EEFF-E6290C54-30C89722",
            "92FF2541-AE11CB82-9B9CB327-F08850F9-2E5EE8F5-935D1154-35F19C24",
            "83FE4C3B-B61DCCD4-6AF3E246-95F9D0F1-DA18BFA7-E7DA0855-63FCCE26",
            "0DFEF83E-E211D284-63F4E775-9EA889EC-D619E2F5-FBC00850-00F0CA26",
            "0001C943-E214C783-91C9FF73-3E4117F4-4C0BEEEB-1D5C0F55-33FB9B7F",
            "70FE8A37-B610CAD4-C1C9B373-CE8DD2F3-8E50E8F2-A8490855-39FBCC77",
            "3CFFC92E-77F7A2A0-BF8624F6-E6A16523-F9623922-23F20254-35AB9B26");
    // ciphers = new ArrayList<>();
    // for (int i = 0; i < 50; i++) {
    // ciphers.add(
    // new HyperspaceCoordinates(100, new Coordinates(1, 2, 3), Constants.SURVEY_MK1_ACCURACY)
    // .toString());
    // }

    Collection<SimpleEntry<Integer, byte[]>> secretSegments =
        ciphers.parallelStream().map(n -> breakCipher(n)).collect(Collectors.toList());

    secretSegments.stream().forEach(n -> {
      System.out.println(
          n.getKey() + " " + Arrays.toString(n.getValue()) + " " + new String(n.getValue()));
    });

    String secret = assembleSecret(secretSegments);
    System.out.println("SECRET: " + secret);
  }

  private static SimpleEntry<Integer, byte[]> breakCipher(String cipher) {
    byte[] bytes = HexUtil.toByteArray(cipher.replace("-", ""));
    int offset = Byte.toUnsignedInt(bytes[0]);
    byte[] encrypted = Arrays.copyOfRange(bytes, 24, 28);
    HexUtil.toByteArray(cipher.replace("-", "").substring(48, 56));
    byte[] decrypted = ByteBuffer.allocate(KNOWN_LENGTH_IN_BYTES).order(ByteOrder.LITTLE_ENDIAN)
        .putFloat(Constants.SURVEY_MK1_ACCURACY).array();
    byte[] secret = new byte[KNOWN_LENGTH_IN_BYTES];

    for (int i = 0; i < encrypted.length; i++) {
      secret[i] = (byte) (encrypted[i] ^ decrypted[i]);
    }

    return new SimpleEntry<Integer, byte[]>(offset, secret);
  }

  /**
   * N.B.: Assumes no sequence of two consecutive characters repeat.
   * 
   * @param secretSegmentsInByte
   * @return {@link String}
   */
  private static String assembleSecret(
      Collection<SimpleEntry<Integer, byte[]>> secretSegmentsInByte) {
    Collection<String> secretSegments = secretSegmentsInByte.parallelStream()
        .map(n -> new String(n.getValue())).collect(Collectors.toSet());

    String secretSegment = secretSegments.iterator().next();
    secretSegments.remove(secretSegment);
    final StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(secretSegment);
    boolean secretIsComplete = false;

    do {
      String last2Chars = stringBuilder.substring(stringBuilder.length() - OVERLAP_LENGTH);
      secretSegment = secretSegments.parallelStream().filter(n -> n.contains(last2Chars))
          .findFirst().orElseThrow(() -> new RuntimeException(
              "Not enough secret segments. Current secret: " + stringBuilder.toString()));
      stringBuilder
          .append(secretSegment.substring(secretSegment.lastIndexOf(last2Chars) + OVERLAP_LENGTH));
      secretSegments.remove(secretSegment);

      if (stringBuilder.substring(stringBuilder.length() - OVERLAP_LENGTH)
          .equals(stringBuilder.substring(0, OVERLAP_LENGTH))) {
        secretIsComplete = true;
      }
    } while (!secretIsComplete);

    return stringBuilder.toString().substring(OVERLAP_LENGTH);
  }
}
