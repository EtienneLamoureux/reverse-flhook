package com.etiennelamoureux.reverseflhook.jump;

import com.etiennelamoureux.reverseflhook.utils.Constants;
import com.etiennelamoureux.reverseflhook.utils.HexUtil;
import com.etiennelamoureux.reverseflhook.utils.TimeUtil;
import java.nio.ByteBuffer;

/**
 * Builds a realistic hyperspace coordinates string to be used after the <code>/setcoords/<code>
 * command. Indistinguishable from a server-generated one.
 * 
 * @see <a href=
 *      "https://github.com/DiscoveryGC/FLHook/blob/9499f8be19611b836afcf292938da78033b9acef/Plugins/Public/playercntl_plugin/HyperJump.cpp#L152">
 *      Reverse-engineered data structure</a>
 */
public class HyperspaceCoordinates {
  private static final int HCOORD_SIZE = 28;

  private short parity;
  private short seed;
  private int system;
  private Coordinates coordinates;
  private int time;
  private float accuracy;

  public HyperspaceCoordinates(int system, Coordinates coordinates) {
    this.seed = (short) (Math.random() * Short.MAX_VALUE);
    this.system = system;
    this.coordinates = coordinates;
    this.time = TimeUtil.secondsFrom1970() - TimeUtil.upTo1WeekInSeconds()
        + Constants.HYPERSPACE_COORDINATES_LIFETIME;
    this.accuracy = Constants.SURVEY_MK3_ACCURACY;
    this.parity = calculateParity();
  }

  HyperspaceCoordinates(String string) {
    string = string.replace("-", "");
    byte[] bytes = encrypt(HexUtil.toByteArray(string));

    parity = ByteBuffer.wrap(new byte[] {bytes[0], bytes[1]}).getShort();
    seed = ByteBuffer.wrap(new byte[] {bytes[2], bytes[3]}).getShort();
    system = ByteBuffer.wrap(new byte[] {bytes[4], bytes[5], bytes[6], bytes[7]}).getInt();
    coordinates = new Coordinates(
        ByteBuffer.wrap(new byte[] {bytes[8], bytes[9], bytes[10], bytes[11]}).getFloat(),
        ByteBuffer.wrap(new byte[] {bytes[12], bytes[13], bytes[14], bytes[15]}).getFloat(),
        ByteBuffer.wrap(new byte[] {bytes[16], bytes[17], bytes[18], bytes[19]}).getFloat());
    time = ByteBuffer.wrap(new byte[] {bytes[20], bytes[21], bytes[22], bytes[23]}).getInt();
    accuracy = ByteBuffer.wrap(new byte[] {bytes[24], bytes[25], bytes[26], bytes[27]}).getFloat();

    if (parity != calculateParity()) {
      throw new RuntimeException("Parity error");
    }
  }

  private short calculateParity() {
    byte[] bytes = getBytes();
    short parity = 0;

    for (int i = 2 /* Skip parity */; i < HCOORD_SIZE; i++) {
      parity += Byte.toUnsignedInt(bytes[i]);
    }

    return parity;
  }

  @Override
  public String toString() {
    return format(encrypt(getBytes()));
  }

  private String format(byte[] bytes) {
    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < HCOORD_SIZE; i++) {
      if (i != 0 && (i % 4) == 0) {
        stringBuilder.append("-");
      }

      stringBuilder.append(HexUtil.toHexString(bytes[i]));
    }

    return stringBuilder.toString();
  }

  private byte[] encrypt(byte[] bytes) {
    byte[] ciphertext = new byte[bytes.length];
    ciphertext[0] = bytes[0];
    ciphertext[1] = bytes[1];

    for (int i = 2 /* Skip parity */, p =
        bytes[0] % (Constants.SECRET.length - 1); i < HCOORD_SIZE; i++, p++) {
      if (p >= Constants.SECRET.length) {
        p = 0;
      }

      ciphertext[i] = (byte) (bytes[i] ^ Constants.SECRET[p]);
    }

    return ciphertext;
  }

  private byte[] getBytes() {
    ByteBuffer byteBuffer = ByteBuffer.allocate(HCOORD_SIZE);
    byteBuffer.putShort(parity);
    byteBuffer.putShort(seed);
    byteBuffer.putInt(system);
    byteBuffer.putFloat(coordinates.getX());
    byteBuffer.putFloat(coordinates.getY());
    byteBuffer.putFloat(coordinates.getZ());
    byteBuffer.putInt(time);
    byteBuffer.putFloat(accuracy);

    return byteBuffer.array();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Float.floatToIntBits(accuracy);
    result = prime * result + ((coordinates == null) ? 0 : coordinates.hashCode());
    result = prime * result + parity;
    result = prime * result + seed;
    result = prime * result + system;
    result = prime * result + time;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    HyperspaceCoordinates other = (HyperspaceCoordinates) obj;
    if (Float.floatToIntBits(accuracy) != Float.floatToIntBits(other.accuracy))
      return false;
    if (coordinates == null) {
      if (other.coordinates != null)
        return false;
    } else if (!coordinates.equals(other.coordinates))
      return false;
    if (parity != other.parity)
      return false;
    if (seed != other.seed)
      return false;
    if (system != other.system)
      return false;
    if (time != other.time)
      return false;
    return true;
  }
}
