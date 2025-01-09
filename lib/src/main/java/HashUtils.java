import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;

public class HashUtils {
  String hashSha256(String base) {
    String hash = Hashing.sha256()
        .hashString(base, StandardCharsets.UTF_8)
        .toString();
    return hash;
  }
}
