package zad1;

import java.util.ListResourceBundle;

public class Info_pl_PL extends ListResourceBundle {

    private Object[][] contents = {
            {"Japan", "Japonia"},
            {"United States", "Stany Zjednoczone Ameryki"},
            {"Italy", "Włochy"},
            {"lake", "jezioro"},
            {"sea", "morze"},
            {"mountains", "góry"},
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
