import java.io.*;
import java.util.Scanner;

public class Gra {
    public static void quiz() throws FileNotFoundException {


//        File folder = new File("src/main/resources");
//        File[] listaPlikow = folder.listFiles();
//
        File file = new File("src/main/resources/Animals.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String pytanie = scanner.nextLine();
            System.out.println(pytanie);

            String ileOdp = scanner.nextLine();
            int ileOdpowiedzi = Integer.parseInt(ileOdp);
            for (int i = 0; i<ileOdpowiedzi; i++) {
                String odp = scanner.nextLine();
                System.out.println(odp);
            }

        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        quiz();
    }
}
