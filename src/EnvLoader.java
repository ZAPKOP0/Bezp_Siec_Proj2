import java.io.*;
import java.util.*;

public class EnvLoader {

    public static Map<String, String> load() {
        Map<String, String> env = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(".env"))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains("=")) {
                    String[] parts = line.split("=", 2);
                    env.put(parts[0].trim(), parts[1].trim());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Nie można wczytać .env", e);
        }

        return env;
    }
}
