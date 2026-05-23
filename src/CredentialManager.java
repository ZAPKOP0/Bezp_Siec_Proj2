import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;

import java.io.FileInputStream;
import java.util.Properties;

public class CredentialManager {

    private final String username;
    private final String password;

    public CredentialManager() throws Exception {

        Properties properties = new Properties();

        properties.load(new FileInputStream("encrypted.properties"));

        PooledPBEStringEncryptor encryptor = CryptoConfig.getEncryptor();

        username = encryptor.decrypt(properties.getProperty("encrypted.username"));
        password = encryptor.decrypt(properties.getProperty("encrypted.password"));

        System.out.println("USER = " + username);
        System.out.println("PASSWORD = " + password);

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}