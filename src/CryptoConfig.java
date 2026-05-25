import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

import java.util.Map;
import java.util.Scanner;

public class CryptoConfig {

    public static PooledPBEStringEncryptor getEncryptor(int option) {

        String password;

        switch (option) {

            case 1:
                password = System.getenv("ENCRYPTOR_PASSWORD");
                break;

            case 2:
                Map<String, String> env = EnvLoader.load();

                password = env.get("ENCRYPTOR_PASSWORD");
                break;

            case 3:

                Scanner scanner = new Scanner(System.in);

                System.out.print("Podaj master password: ");
                password = scanner.nextLine();
                break;

            default:
                throw new IllegalArgumentException(
                        "Niepoprawna opcja."
                );
        }

        if (password == null || password.isBlank()) {
            throw new RuntimeException(
                    "Brak hasła szyfrującego."
            );
        }

        SimpleStringPBEConfig config =
                new SimpleStringPBEConfig();

        config.setPassword(password);
        config.setAlgorithm("PBEWithHMACSHA512AndAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setStringOutputType("base64");

        PooledPBEStringEncryptor encryptor =
                new PooledPBEStringEncryptor();

        encryptor.setConfig(config);

        return encryptor;
    }
}