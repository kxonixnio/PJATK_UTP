public class Main {

    public static void main(String[] args) {


        ListaTowarow listaTowarow = new ListaTowarow();
        WatekA A = new WatekA(listaTowarow);
        WatekB B = new WatekB(listaTowarow);
        A.start();
        B.start();

        try {
            A.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            B.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}