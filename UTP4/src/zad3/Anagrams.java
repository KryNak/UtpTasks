/**
 * @author Nakielski Krystian S20258
 */

package zad3;


import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Anagrams {

    private List<List<String>> values;

    public Anagrams(String input) {

        List<String> words = new ArrayList<>();

        try {

            for (Scanner s = new Scanner(Paths.get(input)); s.hasNext(); ) {
                words.add(s.next().trim());
            }


        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        values = new ArrayList<>(
                words.stream()
                        .collect(
                                Collectors.groupingBy(
                                        l -> Stream.of(l.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())),
                                        Collectors.toList()
                                )
                        )
                        .values()
        );

        values.forEach(l -> l.sort(String::compareTo));
        values.sort(Comparator.comparing((Function<List<String>, Integer>)List::size).thenComparing(o -> o.get(0)).reversed());

    }

    public List<List<String>> getSortedByAnQty() {

        return values;
    }

    public String getAnagramsFor(String word) {

        List<String> temp = Collections.emptyList();
        boolean isInFile = false;

        for (List<String> l : values) {
            if (l.contains(word)) {
                temp = new ArrayList<>(l);
                temp.remove(word);
                isInFile = true;
            }
        }

        return isInFile ? word + ": " + temp : null;
    }
}
