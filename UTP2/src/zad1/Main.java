/**
 *
 *  @author Tomczyk Mikołaj S22472
 *
 */

package zad1;


import java.util.*;

public class Main {

  static List<String> getPricesInPLN(List<String> destinations, double xrate) {
    return ListCreator.collectFrom(destinations)
                       .when((String destination) -> destination.startsWith("WAW") /*<-- lambda wyrażenie
                                *  selekcja wylotów z Warszawy (zaczynających się od WAW)
                                */
                        )
                       .mapEvery((String destination) -> {
                         List<String> departure_splitted = Arrays.asList(destination.split(" "));
                         Integer price_in_EUR = Integer.parseInt(departure_splitted.get(2));
                         Double price_in_PLN = price_in_EUR * xrate;

                         StringBuilder bobTheBuilder = new StringBuilder("to ");
                         bobTheBuilder
                                 .append(departure_splitted.get(1))
                                 .append(" - price in PLN: ")
                                 .append(price_in_PLN.intValue());

                         return bobTheBuilder.toString();

                               } /*<-- lambda wyrażenie
                                   *  wyliczenie ceny przelotu w PLN
                                   *  i stworzenie wynikowego napisu
                                   */
                        );
  }

  public static void main(String[] args) {
    // Lista destynacji: port_wylotu port_przylotu cena_EUR 
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = getPricesInPLN(dest, ratePLNvsEUR);
    for (String r : result) System.out.println(r);
  }
}
