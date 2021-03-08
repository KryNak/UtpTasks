package zad1;

import java.util.*;
import java.util.stream.Collectors;

public class LocaleUtilities {

    private LocaleUtilities(){

    }

    public static Locale parseLocale(String localeStr){
        String[] splitLoc = localeStr.split("_");
        Locale locale;

        switch (splitLoc.length){
            case 1:
                locale = new Locale(splitLoc[0]);
                break;
            case 2:
                locale = new Locale(splitLoc[0], splitLoc[1]);
                break;
            case 3:
                locale = new Locale(splitLoc[0], splitLoc[1], splitLoc[2]);
                break;
            default:
                throw new RuntimeException("Wrong argument in locale.");
        }

        return locale;
    }

    public static String translateNation(Locale defaultLocale, String nation, Locale targetLocale){

        if(defaultLocale.getLanguage().equals(targetLocale.getLanguage())){
            return nation;
        }

        Locale prevLocale = Locale.getDefault();
        Locale.setDefault(defaultLocale);

        Map<String, Locale> nationToLocale = Arrays
                .stream(Locale.getAvailableLocales())
                .collect(Collectors.toMap(Locale::getDisplayCountry, loc -> loc, (first, second) -> first));

        String translation = nationToLocale
                .getOrDefault(nation, new Locale("pl", "PL"))
                .getDisplayCountry(targetLocale);

        Locale.setDefault(prevLocale);
        return translation;
    }

}
