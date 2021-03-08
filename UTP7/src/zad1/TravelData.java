package zad1;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TravelData {

    private final List<Offer> offers;

    public TravelData(File dataDir) {

        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.txt");
        offers = new ArrayList<>();

        try {

            Files.walk(dataDir.toPath())
                    .filter(e -> e.toFile().isFile())
                    .filter(e -> matcher.matches(e.getFileName()))
                    .forEach(e -> {
                        try {
                            Files.readAllLines(e).forEach(i -> offers.add(new Offer(i)));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<String> getOffersDescriptionsList(String locale, String dateFormat) {
        return offers
                .stream()
                .map(e -> e.toFormattedString(locale, dateFormat))
                .collect(Collectors.toList());
    }

    public List<Offer> getOffers() {
        return offers;
    }
}
