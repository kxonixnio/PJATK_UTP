/**
 * @author Tomczyk Mikołaj S22472
 */

package zad1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CustomersPurchaseSortFind {

    private ArrayList<Purchase> purchaseList;

    public void readFile(String fname) {
        this.purchaseList = new ArrayList<Purchase>();

        try (BufferedReader br = new BufferedReader(new FileReader(fname))) {
            String line;
            while ((line = br.readLine()) != null) {
                purchaseList.add(new Purchase(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //https://stackoverflow.com/questions/5868369/how-can-i-read-a-large-text-file-line-by-line-using-java

    public void showSortedBy(String filterName) {
        System.out.println(filterName);
        List<Purchase> copyPurchaseList = new ArrayList<>(purchaseList);

        copyPurchaseList.sort(sortByID());

        if (filterName.equals("Nazwiska")) {
            copyPurchaseList.sort(sortBySurname());
            copyPurchaseList.forEach(System.out::println);

        } else if (filterName.equals("Koszty")) {
            copyPurchaseList.sort(sortByCost());

            for (Purchase purchase : copyPurchaseList) {
                System.out.print(purchase);
                System.out.println(" (koszt: " + purchase.getQuantity() * purchase.getPrice() + ")");
            }
        }
        System.out.println();
    }
    //https://stackoverflow.com/questions/554769/alphabetically-sort-a-java-collection-based-upon-the-tostring-value-of-its-mem

    public void showPurchaseFor(String id) {
        System.out.println("Klient " + id);

        List<Purchase> copy = purchaseList.stream().filter(x -> x.getID().equals(id)).collect(Collectors.toList());
        for (Purchase purchase : copy) {
            System.out.println(purchase);
        }

        System.out.println();
    }

    private Comparator<Purchase> sortByID() {
        return ((o1, o2) -> {
            return o1.getID().compareTo(o2.getID());
        });
    }

    private Comparator<Purchase> sortBySurname() {
        return ((o1, o2) -> {
            return o1.getSurname().compareTo(o2.getSurname());
        });
    }

    private Comparator<Purchase> sortByCost() {
        return (o1, o2) -> {
            Double o1Cost = o1.getQuantity() * o1.getPrice();
            Double o2Cost = o2.getQuantity() * o2.getPrice();

            //Descending order
            return o2Cost.compareTo(o1Cost);
        };
    }
}

