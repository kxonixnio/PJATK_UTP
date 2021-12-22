import java.util.ArrayList;

public class ListaTowarow {

    private ArrayList<Towar> listaTowarow = new ArrayList<Towar>();
    private boolean gotowyTowar = false;
    private boolean koniecTowarow = false;

    synchronized Towar getTowar() {
        while (!gotowyTowar && !koniecTowarow) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!koniecTowarow) {
            Towar removed = listaTowarow.remove(0);
            if (listaTowarow.size() == 0) {
                gotowyTowar = false;
            }
            return removed;
        } else
            return null;
    }

    public synchronized boolean czyKoniecTowarow() {
        if (listaTowarow.size() > 0) {
            return false;
        }
        return koniecTowarow;
    }

    public synchronized void ustawKoniecTowarow() {
        koniecTowarow = true;
        notify();
    }

    synchronized void addTowar(Towar towar) {
        listaTowarow.add(towar);
        gotowyTowar = true;
        notify();
    }
}