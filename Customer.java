import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Clase Customer
 * En este caso es la clase que observa (Observer)
 * Observa los cambios que se produzcan en otra clase, a la que se conoce como 
 * Observable
 */
public class Customer implements PropertyChangeListener {
 
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        float newPrice = (float) event.getNewValue();
        float oldPrice = (float) event.getOldValue();
        System.out.println("El precio ha bajado " + (oldPrice - newPrice) + " €");
    }
}