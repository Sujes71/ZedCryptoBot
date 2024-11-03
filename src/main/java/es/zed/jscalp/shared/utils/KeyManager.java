package es.zed.jscalp.shared.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class KeyManager {

  public static String hmacSHA256(String data, String key) {
    try {
      Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
      SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
      sha256_HMAC.init(secret_key);
      return bytesToHex(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
    } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException e) {
      throw new RuntimeException("Error generating HMAC SHA256 signature", e);
    }
  }

  private static String bytesToHex(byte[] bytes) {
    StringBuilder result = new StringBuilder();
    for (byte b : bytes) {
      result.append(String.format("%02x", b));
    }
    return result.toString();
  }

}
