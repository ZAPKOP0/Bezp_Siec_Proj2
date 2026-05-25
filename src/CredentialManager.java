import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;

import java.io.FileInputStream;
import java.util.Properties;

public class CredentialManager {

    private final String username;
    private final String password;

    private CredentialManager(String username,
                              String password) {

        this.username = username;
        this.password = password;
    }

    public static CredentialManager fromEncryptedFile(int option)
            throws Exception {

        Properties properties = new Properties();

        properties.load(
                new FileInputStream("encrypted.properties")
        );

        PooledPBEStringEncryptor encryptor =
                CryptoConfig.getEncryptor(option);

        String username =
                encryptor.decrypt(
                        properties.getProperty(
                                "encrypted.username"
                        )
                );

        String password =
                encryptor.decrypt(
                        properties.getProperty(
                                "encrypted.password"
                        )
                );

        return new CredentialManager(
                username,
                password
        );
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}