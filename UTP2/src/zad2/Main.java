/**
 *
 *  @author Nakielski Krystian S20258
 *
 */

package zad2;


/*<-- niezbędne importy */

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    List<String> result = dest.stream()
            .map(a -> a.split(" "))
            .filter(a -> a.length == 3 && a[0].toUpperCase().equals("WAW"))
            .map(a -> "to " + a[1] + " - price in PLN:\t" + (int)(Double.parseDouble(a[2]) * ratePLNvsEUR) )
            .collect(Collectors.toList());

    for (String r : result) System.out.println(r);
  }
}
