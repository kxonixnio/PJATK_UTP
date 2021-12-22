import java.io.*;
import java.util.Scanner;

public class WatekA extends Thread {

    private ListaTowarow listaTowarow;
    private int liczbaStworzonychTowarow = 0;

    WatekA(ListaTowarow listaTowarow) {
        this.listaTowarow = listaTowarow;
    }

    public void run() {
        try {
            Scanner scanner = new Scanner(new File("Towary.txt"));

            while(scanner.hasNext()) {
                int id = scanner.nextInt();
                int waga = scanner.nextInt();

                listaTowarow.addTowar(new Towar(id, waga));
                liczbaStworzonychTowarow++;

                if(liczbaStworzonychTowarow % 200 == 0) {
                    System.out.println("utworzono " + liczbaStworzonychTowarow + " obiektow");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        listaTowarow.ustawKoniecTowarow();
    }
}