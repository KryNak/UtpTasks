package zad1;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class Offer {

    private final Locale localization;

    public String getNation() {
        return nation;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public String getPlace() {
        return place;
    }

    public double getPrice() {
        return price;
    }

    public String getConcurrencySymbol() {
        return concurrencySymbol;
    }

    private final String nation;
    private final LocalDate departureDate;
    private final LocalDate arrivalDate;
    private final String place;
    private final double price;
    private final String concurrencySymbol;

    public Offer(String travelInformation) {

        String[] splitStr = travelInformation.split("\t");

        this.localization = adjustLocalization(splitStr[0]);
        this.nation = adjustNation(splitStr[1]);
        this.departureDate = adjustDepartureDate(splitStr[2]);
        this.arrivalDate = adjustArrivalDate(splitStr[3]);
        this.place = adjustPlace(splitStr[4]);
        this.price = adjustPrice(splitStr[5]);
        this.concurrencySymbol = adjustConcurrencySymbol(splitStr[6]);
    }

    private Locale adjustLocalization(String localization) {
        return LocaleUtilities.parseLocale(localization);
    }

    private String adjustNation(String nation) {
        return nation;
    }

    private LocalDate adjustDepartureDate(String departureDate) {
        return LocalDate.parse(departureDate);
    }

    private LocalDate adjustArrivalDate(String arrivalDate) {
        return LocalDate.parse(arrivalDate);
    }

    private String adjustPlace(String place) {
        return place;
    }

    private double adjustPrice(String price) {

        double numb = 0;

        try {
            numb = NumberFormat.getInstance(localization).parse(price).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return numb;
    }

    private String adjustConcurrencySymbol(String concurrencySymbol) {
        return concurrencySymbol;
    }


    @Override
    public String toString() {
        return nation + "\t" + departureDate + "\t" + arrivalDate + "\t" + place + "\t" + price + "\t" + concurrencySymbol;
    }

    public String toFormattedString(String locale, String dateFormat) {

        Locale argLocalization = LocaleUtilities.parseLocale(locale);

        String nation = LocaleUtilities.translateNation(localization, this.nation, argLocalization);
        String departureDate = this.departureDate.format(DateTimeFormatter.ofPattern(dateFormat));
        String arrivalDate = this.arrivalDate.format(DateTimeFormatter.ofPattern(dateFormat));
        String place = this.place;
        String price = NumberFormat.getInstance(argLocalization).format(this.price);
        String concurrencySymbol = this.concurrencySymbol;

        if(!argLocalization.getLanguage().equals(localization.getLanguage())){
            ResourceBundle bundle = ResourceBundle.getBundle("zad1.Info", argLocalization);

            place = bundle.getString(place);
        }

        return String.join("\t", new String[]{
                nation,
                departureDate,
                arrivalDate,
                place,
                price,
                concurrencySymbol
        });
    }
}
