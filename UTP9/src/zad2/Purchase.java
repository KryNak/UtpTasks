/**
 * @author Nakielski Krystian S20258
 */

package zad2;


import java.beans.*;

public class Purchase {

    private String prod;
    private String data;
    private Double price;

    public Purchase(String prod, String data, Double price) {
        this.prod = prod;
        this.data = data;
        this.price = price;
    }

    public synchronized void setData(String newData) {
        String oldData = data;

        propertyChangeSupport.firePropertyChange("data", oldData, newData);

        data = newData;
    }

    public synchronized void setPrice(Double newPrice) throws PropertyVetoException {
        Double oldPrice = price;

        vetoableChangeSupport.fireVetoableChange("price", oldPrice, newPrice);
        propertyChangeSupport.firePropertyChange("price", oldPrice, newPrice);

        price = newPrice;
    }

    public void setProd(String newProd) {
        this.prod = prod;
    }

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);

    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public synchronized void addVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeSupport.addVetoableChangeListener(listener);
    }

    public synchronized void removeVetoableChangeListener(VetoableChangeListener listener){
        vetoableChangeSupport.removeVetoableChangeListener(listener);
    }

    @Override
    public String toString() {
        return "Purchase [" +
                "prod=" + prod +
                ", data=" + data +
                ", price=" + price +
                ']';
    }
}
