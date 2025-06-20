import java.util.HashMap;

public class InventoryManager {
    private HashMap<String, Product> inventory = new HashMap<>();

    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
        System.out.println("Product added: " + product);
    }

    public void updateProduct(String productId, int quantity, double price) {
        Product product = inventory.get(productId);
        if (product != null) {
            product.setQuantity(quantity);
            product.setPrice(price);
            System.out.println("Updated: " + product);
        } else {
            System.out.println("Product not found.");
        }
    }

    public void deleteProduct(String productId) {
        Product removed = inventory.remove(productId);
        if (removed != null) {
            System.out.println("Deleted: " + removed);
        } else {
            System.out.println("Product not found.");
        }
    }

    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Current Inventory:");
            for (Product product : inventory.values()) {
                System.out.println(product);
            }
        }
    }
}
