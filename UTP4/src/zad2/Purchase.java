/**
 *
 *  @author Nakielski Krystian S20258
 *
 */

package zad2;

public class Purchase {

    private String id;
    private String firstName;
    private String lastName;
    private String goodName;
    private double price;
    private double amount;

    private Purchase(String id, String firstName, String lastName, String goodName, double price, double amount) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.goodName = goodName;
        this.price = price;
        this.amount = amount;
    }

    public static class Builder{
        private String id;
        private String firstName;
        private String lastName;
        private String goodName;
        private double price;
        private double amount;

        public Purchase build(){
            return new Purchase(id, firstName, lastName, goodName, price, amount);
        }

        public Builder setId(String id){
            this.id = id;
            return this;
        }

        public Builder setFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public Builder setGoodName(String goodName){
            this.goodName = goodName;
            return this;
        }

        public Builder setPrice(double price){
            this.price = price;
            return this;
        }

        public Builder setAmount(double amount){
            this.amount = amount;
            return this;
        }
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGoodName() {
        return goodName;
    }

    public double getPrice() {
        return price;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return id + ";" + lastName + " " + firstName + ";" + price + ";" + amount;
    }
}
