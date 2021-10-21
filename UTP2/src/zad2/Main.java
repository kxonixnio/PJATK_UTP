/**
 *
 *  @author Tomczyk Mikołaj S22472
 *
 */

package zad2;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors; /*<-- niezbędne importy */

public class Main {

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
    List<String> result = dest
            .stream()
            .filter((String departure) -> departure.startsWith("WAW"))
            .map(
                    (String departure) -> {
                      String to_where = departure.substring(departure.indexOf(" ") + 1, departure.lastIndexOf(" "));
                      Integer price_in_EUR = Integer.parseInt(departure.substring(departure.lastIndexOf(" ") + 1));
                      Double price_in_PLN = price_in_EUR * ratePLNvsEUR;

                      StringBuilder bobTheBuilder = new StringBuilder("to ");
                      bobTheBuilder
                              .append(to_where)
                              .append(" - price in PLN: ")
                              .append(price_in_PLN.intValue());

                      return bobTheBuilder.toString();
                    }
            ).collect(Collectors.toList());
    /*<-- tu należy dopisać fragment
     * przy czym nie wolno używać żadnych własnych klas, jak np. ListCreator
     * ani też żadnych własnych interfejsów
     * Podpowiedź: należy użyć strumieni
     */

    for (String r : result) System.out.println(r);
  }
}

//niepotrzebnie użyto konstrukcji bezpośrednio tworzących listę


/*
List<String> result = dest
            .stream()
            .filter((String departure) -> departure.startsWith("WAW"))
            .map(
                    (String destination) -> destination
                            .concat(" to ")
                            .concat(Arrays.asList(destination.split(" ")).get(1))
                            .concat(" - price in PLN: ")
                            .concat(
                                    String.valueOf(
                                            (int)(Integer.parseInt(Arrays.asList(destination.split(" ")).get(2))*ratePLNvsEUR)
                                    )
                            )
            ).map(
                    (String string) -> string
                            .substring(string.indexOf(" ") + 1)
                            .substring(string.indexOf(" ") + 1)
                            .substring(string.indexOf(" ") + 2)
            ).collect(Collectors.toList());
 */