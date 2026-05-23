import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserService {

    private final Connection connection;

    public UserService(
            Connection connection
    ) {
        this.connection = connection;
    }

    public void registerUser(
            User user
    ) throws Exception {

        String hashedPassword =
                BCrypt.hashpw(
                        user.getPassword(),
                        BCrypt.gensalt()
                );

        String sql =
                "INSERT INTO users(username,password) VALUES (?,?)";

        PreparedStatement statement =
                connection.prepareStatement(sql);

        statement.setString(
                1,
                user.getUsername()
        );

        statement.setString(
                2,
                hashedPassword
        );

        statement.executeUpdate();

        System.out.println(
                "Użytkownik zapisany poprawnie"
        );
    }
}