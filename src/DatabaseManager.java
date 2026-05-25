import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseManager {

    private final CredentialManager credentials;
    private Connection connection;

    public DatabaseManager(CredentialManager credentials) {
        this.credentials = credentials;
    }

    public void connect() throws Exception {

        connection =
                DriverManager.getConnection(
                        "jdbc:h2:./securedb;DB_CLOSE_DELAY=-1",
                        credentials.getUsername(),
                        credentials.getPassword()
                );

        createTable();
    }

    private void createTable() throws Exception {

        String sql = """
                CREATE TABLE IF NOT EXISTS users(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    username VARCHAR(255) UNIQUE,
                    password VARCHAR(255)
                )
                """;

        Statement statement =
                connection.createStatement();

        statement.execute(sql);
    }

    public Connection getConnection() {
        return connection;
    }
}