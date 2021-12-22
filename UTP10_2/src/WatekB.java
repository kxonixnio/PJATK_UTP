public class WatekB extends Thread {

    private ListaTowarow listaTowarow;
    private int suma = 0;
    private int zliczoneTowary = 0;

    WatekB(ListaTowarow listaTowarow) {
        this.listaTowarow = listaTowarow;
    }

    public void run() {
        while (!listaTowarow.czyKoniecTowarow()) {
            Towar towar = listaTowarow.getTowar();

            if (towar != null) {
                suma += towar.getWaga();
                zliczoneTowary++;

                if (zliczoneTowary % 100 == 0) {
                    System.out.println("policzono wage " + zliczoneTowary + " towarow");
                }
            }
        }
        System.out.println(suma);
    }
}