public class Main {

    public static void main(String[] args) {

        try {

            CredentialManager credentials =
                    new CredentialManager();

            if (credentials.getUsername() != null
                    && credentials.getPassword() != null) {

                System.out.println(
                        "Wszystko OK - dane zostały poprawnie odczytane."
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