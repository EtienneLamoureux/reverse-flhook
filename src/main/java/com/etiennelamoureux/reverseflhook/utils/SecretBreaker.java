package com.etiennelamoureux.reverseflhook.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
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
        Arrays.asList("D5FFD205-7EEAE6AE-1817E425-C660C976-DFE23276-7EEF0454-38E59B72",
            "78FF5323-298D96A8-314F5B71-A1BF11F1-C4D381F5-04EA3758-65A99F7F",
            "A6FF542A-3BFB98D8-C6CDA846-7F8A40F5-CAC832A4-97ED0355-63FCCE26",
            "70FE8A37-B610CAD4-C1C9B373-CE8DD2F3-8E50E8F2-A8490855-39FBCC77",
            "D1FF0519-BB419E87-7F9FB627-213E24F5-2B52BAA7-C1B40859-33FD9C74",
            "E8FF0C24-B71CD283-7292E125-613D20C0-7003BAF6-87AE5F01-2DFD9B72",
            "1800614D-AE10CA8E-7DC8E272-CBBDD8A5-3852E3F2-5FF91655-34FDCC73",
            "A9FE6727-E014CB87-5093B727-E2BF9FA4-0A4FEEF2-91FB0F01-61FD8274",
            "FCFE8374-B610CAD4-6EA6B373-80AF88F2-D71FE8F2-4EDC0855-39FBCC77",
            "D0FFF034-831C9AD6-7793E622-6D2625F4-7D05EFA7-18AC1653-39FB9A74",
            "64032E1C-7F2A9783-1D3D67F7-417F2722-4751F573-15C95601-35E59C7E",
            "3CFFC92E-77F7A2A0-BF8624F6-E6A16523-F9623922-23F20254-35AB9B26",
            "E1FD6830-AE17C684-F831E772-5EAD19F5-82BDBFF6-B7C23C58-65A99F7F",
            "3AFE946D-BB09CB82-F431B776-52B02DF8-DEEFECFE-DE965D4D-35FC9A24",
            "07FF8847-B610CAD4-F963B573-4BB314F3-8EBDEFF2-5BDE0855-39FBCC77",
            "3EFEE26E-BA179C87-F932D47E-03E11DF8-8FEABDEB-8EC00903-34A9CE72",
            "E0FE9018-B609CC8E-FE37E775-52B800F4-8EB7EFA5-13C10C60-38ADCE77",
            "9FFF7169-B617CC83-5ECCE673-812DD6F1-3917DBFE-A99B0C58-34ACCE6A",
            "F6FF7939-B31CCBD3-07CCE772-8D7D81A0-6C12F6F5-69C80953-33FC976A",
            "E5FE6822-B617CC83-8995E673-64BCCCF1-0D00DBFE-E33F0D58-34ACCE6A",
            "2CFF3931-B647CBD6-D08DFF75-64BC9AF2-0A04E3EB-28550453-63F89B77",
            "EA00C95F-AE10CA8E-CB46E272-D32BB224-3956E3F2-64001055-34FDCC73",
            "70010334-B610CAD4-CC44B373-CE18B372-6D55E8F2-574C0955-39FBCC77",
            "00010A28-E214C783-9C44FF73-D71EE975-3907EEEB-C8580E55-33FB9B7F",
            "C100B469-B011CC84-CC1DFF72-D612B922-6852EBC6-5B075C50-38FCCB26",
            "EF00020F-E014CB87-F81DB727-D313BE25-394BEEF2-C4010901-61FD8274",
            "46FEAD0E-E214C783-A463F973-52B54EF4-E5EFE9EB-FB5A0E55-33FB9B7F",
            "D7FEA21B-E209CA83-F561E027-07B500F3-BDBDE9F5-7050054D-34FD9674",
            "CFFEF94D-B324C7D2-A132EC72-02E100F5-B0BBBFF2-6C04084D-33F19C72",
            "06FEFF30-AE11CB82-A336B527-53AD1EF9-B7BBEFF5-AD5E1054-35F19C24",
            "34FFD732-BA17CA84-AE96EA6B-923C3AF2-BD56EFF6-5B4F5801-30F09B23",
            "22FF2919-BB419E87-9296B627-1F1BEAF5-E852BAA7-F85A0E59-33FD9C74",
            "EBFF9921-B711C684-CB92E676-04123BA1-2151EFA2-24550854-35AB9B26",
            "F1FE7B2C-B714FF8F-C2C3E27E-9F805AED-DA53EEA5-9F195C55-2DFB9674",
            "26FFE859-B7409E9A-53D5E725-8C7FD4F4-2014E2F5-8EC80F54-38E59B72",
            "44FF3C17-831C9AD6-56D9E622-D93380F5-3844EFA7-83C91153-39FB9A74",
            "45FF2742-BB419E87-5ED5B627-952B81F4-6E13BAA7-8DD00F59-33FD9C74",
            "8AFE6678-831C9AD6-158AE622-D0A59BF5-F752EFA7-88111053-39FB9A74",
            "0CFF0603-E245CA9A-168BE173-82BB9AF9-EF05EEFF-5B460D54-30C89722",
            "88FE5D43-B714FF8F-40D3E27E-85ECCFEC-F705EEA5-FB445C55-2DFB9674",
            "B9FE2652-E0109ED6-109FE17F-82BD9DF2-F609F6F2-021F0E03-30FC9F47",
            "96FE5A48-E0109ED6-109FE17F-82BD9DF2-F609F6F2-971F0E03-30FC9F47",
            "10FFFE35-B01DCC82-5ED7E67E-B3A180F8-6745EBF2-11270505-61F89773",
            "57FE9F22-BA17CA84-5ED0EA6B-AAA08CF2-3716EFF6-881F5801-30F09B23",
            "43FF083B-B324C7D2-0CD4EA72-FAF498F4-6013B8F2-9146084D-33F19C72",
            "2800410F-E209CA83-EB61E027-07B500F3-CEBDE9F5-5401054D-34FD9674",
            "48FF5D69-BB109BD6-F337E073-05B44CA1-C2A3EFFF-E1000E53-34F08273",
            "33FFD806-B01DCC82-ED31E07E-4BB418F9-C4EDECF2-09360505-61F89773",
            "B3FEEA6B-E745D282-EA37B772-07E118ED-C4B7EFF3-93050958-2DFC9A7E",
            "DFFEAD3A-E211D284-E731E175-55B415ED-C3BBE5F5-6B070950-00F0CA26",
            "79FF5719-B01DCC82-FB89E67E-7B909AF8-E94CEBF2-75380505-61F89773",
            "49FF6460-B7409E9A-FD8EE725-62C5CEF4-F71CE2F5-CB0A0E54-38E59B72",
            "75FEE354-E245CA9A-FB83E173-E5979BF9-F71BEEFF-47580D54-30C89722",
            "4E004D32-B7119C83-169DE46B-77229674-A888EFFE-060A0859-33AB9F73",
            "89FFDA17-B324C7D2-16CCE972-207A8874-AF8EB8F2-DE5E084D-33F19C72",
            "FEFEE71A-B647CBD6-DC37F975-5FB318F3-66BAE4EB-E0740453-63F89B77",
            "88FF112D-B714FF8F-D863E47E-52E44CED-60BAE9A5-F8255C55-2DFB9674",
            "DBFF1942-B647CBD6-D08DFF75-64BC9AF2-0A04E3EB-746F0453-63F89B77",
            "C6FEE55D-BB09CB82-888BB176-69BFAFF9-5C51EBFE-B93F5C4D-35FC9A24",
            "B9FEE82D-E0109ED6-8495E17F-6EBA9CF2-0D08F6F2-FF650E03-30FC9F47",
            "06FFA029-AE11CB82-C596B327-CDB032F9-C252E8F5-2E411054-35F19C24",
            "C6FE4614-BB09CB82-8731B776-52B02DF8-F4EFECFE-A61D5C4D-35FC9A24",
            "15FF542F-B010C79A-8A37ED75-05B019F0-91B6B9A7-C9410904-61E59A73",
            "43FF360C-B324C7D2-DF32EC72-02E100F5-A5BBBFF2-041B084D-33F19C72",
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
            "9FFE3F64-B617CC83-62EAE673-94AFD2F1-D61CDBFE-92900C58-34ACCE6A",
            "9C00056E-B01DCC82-A38BE67E-57689AF8-6B53EBF2-694A0405-61F89773",
            "BBFFDC63-E245CA9A-A381E173-496F9BF9-7504EEFF-E6290C54-30C89722",
            "92FF2541-AE11CB82-9B9CB327-F08850F9-2E5EE8F5-935D1154-35F19C24",
            "83FE4C3B-B61DCCD4-6AF3E246-95F9D0F1-DA18BFA7-E7DA0855-63FCCE26",
            "0DFEF83E-E211D284-63F4E775-9EA889EC-D619E2F5-FBC00850-00F0CA26",
            "0001C943-E214C783-91C9FF73-3E4117F4-4C0BEEEB-1D5C0F55-33FB9B7F",
            "F2FE592B-B324C7D2-DF32EC72-02E100F5-A5BBBFF2-AB1B084D-33F19C72",
            "8DFEAF37-B31CCBD3-DF2FE172-53E319A1-F0BBF1F5-F5480853-33FC976A",
            "7DFF700A-B017CB8F-E736E17F-55E31DF4-AF8EE4A3-5E4C0554-64A98272");
    // ciphers = new ArrayList<>();
    // for (int i = 0; i < 50; i++) {
    // ciphers.add(
    // new HyperspaceCoordinates(100, new Coordinates(1, 2, 3), Constants.SURVEY_MK1_ACCURACY)
    // .toString());
    // }

    Collection<SimpleEntry<Integer, byte[]>> secretSegments =
        ciphers.parallelStream().map(n -> breakCipher(n)).collect(Collectors.toList());

    secretSegments.stream().forEach(n -> {
      System.out.println(String.format(Locale.ROOT, "%-3s %-24s %-4s", n.getKey(),
          Arrays.toString(n.getValue()), new String(n.getValue())));
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
