/**
 * @author Nakielski Krystian S20258
 */

package zad2;


/*<-- niezbędne import */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        /*<--
         *  definicja operacji w postaci lambda-wyrażeń:
         *  - flines - zwraca listę wierszy z pliku tekstowego
         *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
         *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
         *  - sum - zwraca sumę elmentów listy liczb całkowitych
         */

        //ExceptionFunction<String, List<String>> flines = e -> Arrays.asList(Files.readString(Paths.get(e)).split("\r\n"));

        ExceptionFunction<String, List<String>> flines = e -> Files.readAllLines(Paths.get(e));
        Function<List<String>, String> join = e -> String.join("", e);
        Function<String, List<Integer>> collectInts = e -> Pattern.compile("[^\\d]")
                .splitAsStream(e)
                .filter(i -> !i.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));
        Function<List<Integer>, Integer> sum = e -> e.stream().mapToInt(Integer::intValue).sum();

        String fname = System.getProperty("user.home") + "/LamComFile.txt";
        InputConverter<String> fileConv = new InputConverter<>(fname);
        List<String> lines = fileConv.convertBy(flines);
        String text = fileConv.convertBy(flines, join);
        List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
        Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

        System.out.println(lines);
        System.out.println(text);
        System.out.println(ints);
        System.out.println(sumints);

        List<String> arglist = Arrays.asList(args);
        InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
        sumints = slistConv.convertBy(join, collectInts, sum);
        System.out.println(sumints);

        // Zadania badawcze:
        // Operacja flines zawiera odczyt pliku, zatem może powstac wyjątek IOException
        // Wymagane jest, aby tę operację zdefiniowac jako lambda-wyrażenie
        // Ale z lambda wyrażeń nie możemy przekazywac obsługi wyjatków do otaczającego bloku
        // I wobec tego musimy pisać w definicji flines try { } catch { }
        // Jak spowodować, aby nie było to konieczne i w przypadku powstania wyjątku IOException
        // zadziałała klauzula throws metody main
    }
}

