public class Application {
    public static void main(String[] args) {
        Product product = new Product("Donuts", "Donuts de chocolate", 10);
        Customer customer = new Customer();
        product.addObserver(customer);
 
        product.decreasePrice(2);
    }
}