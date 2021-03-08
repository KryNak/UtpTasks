package zad1;

public final class Good {

    private final long id;
    private final double weight;

    public Good(String id, String weight){
        this.id = Long.parseLong(id);
        this.weight = Double.parseDouble(weight);
    }

    public long getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return id + " " + weight;
    }
}
