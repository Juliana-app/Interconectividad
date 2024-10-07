package Patron_Observer;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Clase Product
 * Es la clase Observable. Los cambios que se produzcan serán
 * observados inmediatamente por las clases Observer que se registren
 * desde ésta
 */
public class Product {

    private String name;
    private String description;
    private float price;
    private int stock;

    private PropertyChangeSupport support;

    public Product(String name, String description, float price) {
        this.name = name;
        this.description = description;
        this.price = price;
        stock = 0;
        support = new PropertyChangeSupport(this);
    }

    public void addObserver(PropertyChangeListener observer) {
        support.addPropertyChangeListener(observer);
    }

    public void removeObserver(PropertyChangeListener observer) {
        support.removePropertyChangeListener(observer);
    }

    public void decreasePrice(float value) {
        support.firePropertyChange("price", price, (price - value));
        price -= value;
    }
}
