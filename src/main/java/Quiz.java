import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Quiz {
    public static void main(String[] args) throws FileNotFoundException {

        File kategoriaPytan = zakresPytan();
        System.out.println("Wybrałeś tematykę: "+kategoriaPytan.getName());
        List<ZadaniaQuizowe> zadaniaQuizowes = wczytajPlik(kategoriaPytan);
        graj(zadaniaQuizowes);
    }

    private static void graj(List<ZadaniaQuizowe> zadania){
        int punkty =0;
        Collections.shuffle(zadania);
        for (int i =0; i<10 ;i++){
            ZadaniaQuizowe zadanie = zadania.get(i);
            System.out.println("Zadanie "+i+":"+zadanie.pytanie);
//            przed mixowaniem odpowiedzi, trzeba zapamietac, ta ktora jest poprawna
            List<String> odpowiedzi = zadanie.odpowiedzi; //tutaj
            String poprawnaOdpowiedz = odpowiedzi.get(0);
            Collections.shuffle(odpowiedzi);
            for (int j=0; j<odpowiedzi.size(); j++){
                System.out.println(j+") "+odpowiedzi.get(j));
            }

            Scanner scanner = new Scanner(System.in);
            int nrWybranejOdpowiedzi = scanner.nextInt();
            String odpowiedzWybranaPrzezGracza = odpowiedzi.get(nrWybranejOdpowiedzi);
            if (odpowiedzWybranaPrzezGracza.equals(poprawnaOdpowiedz)){
                System.out.println("Poprawna odpowiedź");
                punkty++;
            } else {
                System.out.println("Błąd");
                System.out.println("Poprawna odpowiedź to:"+poprawnaOdpowiedz);
            }
            System.out.println("Zdobyłeś "+punkty+" punktów");
        }
    }

// zad 1
    public static File zakresPytan(){

        File folder = new File("src/main/resources");
        File [] kategorie = folder.listFiles();
        int ileKategrii = kategorie.length;
        for (int i = 1 ; i< ileKategrii; i++){
            String plik = kategorie[i].getName();
            System.out.println(i+" - "+plik);
        }
        System.out.println("Wybierz tematykę (numer), która Cię interesuje");
        Scanner scanner = new Scanner(System.in);
        int nrTematyki = scanner.nextInt();

        File wybranaKategoria = kategorie[nrTematyki];

        return wybranaKategoria;
    }

    // do mieszania odpowiedzi służy Collections.shuffle(jakaś lista)
//  Zad.2   w tej metodzie przekazujemy już pojedynczy plik z pytaniami z wybranej wczesniej
//  tematyki
    private static List<ZadaniaQuizowe> wczytajPlik(File plik) throws FileNotFoundException {
        Scanner scanner = new Scanner(plik);

        List<ZadaniaQuizowe> zadania = new ArrayList<ZadaniaQuizowe>();

        while (scanner.hasNextLine()) {
            ZadaniaQuizowe zadanie = new ZadaniaQuizowe();
            zadanie.pytanie = scanner.nextLine();

//            String pytanie = scanner.nextLine();
//            System.out.println("Pytanie:" + zadanie.pytanie);
//            trzeba parsować, bo jak się zrobi nextInt to może być cośtam ze
//            znakiem końca linii i ogólnie kupa
            String ileOdpowiedzi = scanner.nextLine();
            int ileOdp = Integer.parseInt(ileOdpowiedzi);

            List<String> mozliweOdpowiedzi = new ArrayList<String>(ileOdp);

            for (int i = 0; i < ileOdp; i++) {
                String odpowiedz = scanner.nextLine();
//                System.out.println("Odpowiedz: " + odpowiedz);
                mozliweOdpowiedzi.add(odpowiedz);
            }
            zadanie.odpowiedzi = mozliweOdpowiedzi;
            zadania.add(zadanie);
        }
        return zadania;
    }
}
