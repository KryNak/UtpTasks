/**
 * @author Nakielski Krystian S20258
 */

package zad2;

import java.io.*;
import java.net.URL;
import java.util.*;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Collection<List<String>> values = new BufferedReader(new InputStreamReader(new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt").openStream())).lines().collect(groupingBy(e -> Arrays.stream(e.split("")).collect(groupingBy(identity(), counting())), toList())).values();
        int max = values.stream().max(Comparator.comparingInt(List::size)).get().size();
        values.stream().filter(e -> e.size() == max).map(e -> String.join(" ", e)).forEach(System.out::println);
    }
}
