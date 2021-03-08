package zad4;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProgLang {

    private Map<String, List<String>> langsMap;
    private Map<String, List<String>> progsMap;

    public ProgLang(String input) throws IOException {

        langsMap = new LinkedHashMap<>();
        progsMap = new LinkedHashMap<>();

        LinkedList<String> linkedList = new LinkedList<>();

        for (Scanner s = new Scanner(Paths.get(input)); s.hasNextLine(); ) {
            linkedList.addAll(
                    Stream
                            .of(s.nextLine().split("\t"))
                            .distinct()
                            .collect(Collectors.toList())
            );
            String lang = linkedList.pop();


            langsMap.put(lang, new ArrayList<>(linkedList));


            linkedList.forEach((String e) -> {
                if (progsMap.containsKey(e)) progsMap.get(e).add(lang);
                else progsMap.put(e, new ArrayList<>(Collections.singleton(lang)));
            });


            linkedList.clear();
        }

    }

    public Map<String, List<String>> getLangsMap() {
        return langsMap;
    }

    public Map<String, List<String>> getProgsMap() {
        return progsMap;
    }

    public Map<String, List<String>> getLangsMapSortedByNumOfProgs() {
        return sorted(
                langsMap, Comparator
                        .comparingInt((Map.Entry<String, List<String>> o) -> o.getValue().size())
                        .reversed()
                        .thenComparing(Map.Entry::getKey)
        );
    }


    public Map<String, List<String>> getProgsMapSortedByNumOfLangs() {
        return sorted(
                progsMap, Comparator
                        .comparingInt((Map.Entry<String, List<String>> o) -> o.getValue().size())
                        .reversed()
                        .thenComparing(Map.Entry::getKey)
        );
    }

    public Map<String, List<String>> getProgsMapForNumOfLangsGreaterThan(int i) {
        return filtered(progsMap, (Map.Entry<String, List<String>> m) -> m.getValue().size() > i);
    }

    public <K, V> Map<K, V> sorted(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator) {
        return map
                .entrySet()
                .stream()
                .sorted(comparator)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (s1, s2) -> s1, LinkedHashMap::new));
    }

    public <K, V> Map<K, V> filtered(Map<K, V> map, Predicate<? super Map.Entry<K, V>> predicate) {
        return map
                .entrySet()
                .stream()
                .filter(predicate)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (s1, s2) -> s1, LinkedHashMap::new));
    }

}
