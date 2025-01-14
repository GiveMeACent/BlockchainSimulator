package Utils;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import com.google.common.hash.Hashing;

public class HashUtils {

  public static final Integer ADDRESS_LENGTH = 40;

  public static String generateRandomAddress() {
    SecureRandom secureRandom = new SecureRandom();
    String possibleCharacters = "0123456789abcdef";
    String randomAddress = "0x";
    Integer randomIndex;
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < ADDRESS_LENGTH; i++) {
      randomIndex = secureRandom.nextInt(possibleCharacters.length());
      sb.append(possibleCharacters.charAt(randomIndex));
    }

    randomAddress.concat(sb.toString());

    return randomAddress;
  }

  public static String hashSha256(String base) {
    String hash = Hashing.sha256()
        .hashString(base, StandardCharsets.UTF_8)
        .toString();
    return hash;
  }
}
