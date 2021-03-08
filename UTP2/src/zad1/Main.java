/**
 * @author Nakielski Krystian S20258
 */

package zad1;


import java.util.*;


public class Main {


    static List<String> getPricesInPLN(List<String> destinations, double xrate) {
        return ListCreator.collectFrom(destinations)
                .when(n -> n.split(" ")[0].toUpperCase().equals("WAW"))
                .mapEvery(n -> {
                    String[] strings = n.split(" ");
                    return "to " + strings[1] + " - price in PLN:\t" + (int)(Double.parseDouble(strings[2]) * xrate);
                });
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
