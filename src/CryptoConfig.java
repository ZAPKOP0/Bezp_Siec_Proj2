import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

import java.util.Map;

public class CryptoConfig {

    public static PooledPBEStringEncryptor getEncryptor() {

// V1 zenkryptowanie
//        String password = System.getenv("ENCRYPTOR_PASSWORD");

// V2 pobranie z pliku .env
        Map<String, String> env = EnvLoader.load();


        System.out.println("ENV TEST = " + env.get("ENCRYPTOR_PASSWORD"));
        String password = env.get("ENCRYPTOR_PASSWORD");

        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword(password);
        config.setAlgorithm("PBEWithHMACSHA512AndAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setStringOutputType("base64");

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(config);

        return encryptor;
    }
}