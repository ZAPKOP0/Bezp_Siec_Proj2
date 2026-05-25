import org.h2.tools.Server;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {

            Server webServer = Server.createWebServer(
                    "-web",
                    "-webPort",
                    "8082"
            ).start();

            System.out.println("H2 Console: http://localhost:8082");

            Scanner scanner = new Scanner(System.in);

            System.out.println("Wybierz sposób pobrania master password:");
            System.out.println("1 - zmienna środowiskowa");
            System.out.println("2 - plik .env");
            System.out.println("3 - podczas uruchomienia");

            int cryptoOption =
                    Integer.parseInt(scanner.nextLine());

            CredentialManager credentials =
                    CredentialManager.fromEncryptedFile(
                            cryptoOption
                    );

            if (credentials.getUsername() != null
                    && credentials.getPassword() != null) {

                System.out.println(
                        "Wszystko OK - dane zostały poprawnie odczytane."
                );

                DatabaseManager manager =
                        new DatabaseManager(credentials);
                manager.connect();

                UserService service =
                        new UserService(
                                manager.getConnection()
                        );

                System.out.print("Podaj username: ");
                String username = scanner.nextLine();

                System.out.print("Podaj hasło: ");
                String password = scanner.nextLine();

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