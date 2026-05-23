import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

public class Generate {

    public static void main(String[] args) {

        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword("supertajnehaslo");
        config.setAlgorithm("PBEWithHMACSHA512AndAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setStringOutputType("base64");

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(config);

        System.out.println(encryptor.encrypt("admin"));
        System.out.println(encryptor.encrypt("admin123"));
    }
}