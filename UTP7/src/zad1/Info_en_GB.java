package zad1;

import java.util.ListResourceBundle;

public class Info_en_GB extends ListResourceBundle {

    private final Object[][] contents = {
            {"Japonia", "Japan"},
            {"Włochy", "Italy"},
            {"Stany Zjednoczone Ameryki", "United States"},
            {"jezioro", "lake"},
            {"góry", "mountains"},
            {"morze", "lake"},
    };


    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
