/**
 *
 *  @author Tomczyk Mikołaj S22472
 *
 */

package zad1;

public class Purchase {

   private String id;
   private String name;
   private String surname;
   private String product;
   private double price;
   private double quantity;

    public Purchase(String line) {
        String[] parts = line.split(";");
        this.id = parts[0];

        String[] nameAndSurname = parts[1].split(" ");
        this.surname = nameAndSurname[0];
        this.name = nameAndSurname[1];

        this.product = parts[2];
        this.price = Double.parseDouble(parts[3]);
        this.quantity = Double.parseDouble(parts[4]);
    }

    @Override
    public String toString() {
        return String.join(";",
                this.id,
                this.surname + " " + this.name,
                this.product,
                String.valueOf(this.price),
                String.valueOf(this.quantity));
    }

    public String getSurname() {
        return surname;
    }

    public String getID() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public double getQuantity() {
        return quantity;
    }
}
