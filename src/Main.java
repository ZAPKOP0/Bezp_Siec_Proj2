import org.h2.tools.Server;

import java.util.Scanner;

public class Main {

    public static void main(
            String[] args
    ) {

        try {

            Server webServer = Server.createWebServer("-web", "-webPort", "8082").start();
            System.out.println("H2 Console: http://localhost:8082");

            CredentialManager credentials =
                    new CredentialManager();

            if (credentials.getUsername() != null
                    && credentials.getPassword() != null) {


                System.out.println(
                        "Wszystko OK - dane zostały poprawnie odczytane."
                );


                DatabaseManager manager =
                        new DatabaseManager();

                manager.connect();

                UserService service =
                        new UserService(
                                manager.getConnection()
                        );

                Scanner scanner =
                        new Scanner(System.in);

                System.out.print(
                        "Podaj username: "
                );

                String username =
                        scanner.nextLine();

                System.out.print(
                        "Podaj hasło: "
                );

                String password =
                        scanner.nextLine();

                service.registerUser(
                        new User(
                                username,
                                password
                        )
                );
            }
        } catch (Exception e) {

            System.out.println(
                    "Błąd odczytu lub odszyfrowania danych."
            );

            e.printStackTrace();
        }
    }
}