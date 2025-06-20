public class Main {
    public static void main(String[] args) {
        Product[] products = {
            new Product("P001", "Shoes", "Footwear"),
            new Product("P002", "Laptop", "Electronics"),
            new Product("P003", "Backpack", "Bags"),
            new Product("P004", "Watch", "Accessories")
        };

        System.out.println("Linear Search for 'Laptop':");
        Product result1 = SearchUtility.linearSearch(products, "Laptop");
        System.out.println(result1 != null ? result1 : "Product not found");

        System.out.println("\nBinary Search for 'Backpack':");
        Product result2 = SearchUtility.binarySearch(products, "Backpack");
        System.out.println(result2 != null ? result2 : "Product not found");
    }
}
