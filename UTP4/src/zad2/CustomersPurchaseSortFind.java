/**
 * @author Nakielski Krystian S20258
 */

package zad2;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomersPurchaseSortFind {

    private List<Purchase> list;

    public CustomersPurchaseSortFind() {
        this.list = new ArrayList<>();
    }

    public void readFile(String fname) {
        try {
            List<String> strings = Files.readAllLines(Paths.get(fname));

            for (int i = 0; i < strings.size(); i++) {
                add(strings.get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSortedBy(String by) {

        List<Purchase> copy = new ArrayList<>(list);

        switch (by) {
            case "Nazwiska":
                System.out.println(by);
                copy.sort(Comparator.comparing(Purchase::getLastName).thenComparing(Purchase::getId));
                copy.forEach(System.out::println);
                break;
            case "Koszty":
                System.out.println(by);
                copy.sort(Comparator.comparingDouble( (Purchase e) -> (e.getAmount() * e.getPrice()) ).reversed().thenComparing(Purchase::getId));
                copy.forEach(e -> System.out.println(e + " (koszt: " + (e.getPrice() * e.getAmount()) + ")"));
                break;
            default:
                System.out.println("Brak");
                break;

        }


    }

    public void showPurchaseFor(String id) {
        System.out.println("Klient " + id);
        for(Purchase p : list){
            if(p.getId().equals(id)) System.out.println(p);
        }
    }

    private void add(String order) {
        String[] splitArr = order.split(";");

        String id = splitArr[0];
        String goodName = splitArr[2];
        double price = Double.parseDouble(splitArr[3]);
        double amount = Double.parseDouble(splitArr[4]);

        String[] lastNameAndFirstName = splitArr[1].split(" ");
        String lastName = lastNameAndFirstName[0];
        String firstName = lastNameAndFirstName[1];

        Purchase purchase = new Purchase.Builder()
                .setId(id)
                .setLastName(lastName)
                .setFirstName(firstName)
                .setGoodName(goodName)
                .setPrice(price)
                .setAmount(amount).build();

        list.add(purchase);
    }
}
