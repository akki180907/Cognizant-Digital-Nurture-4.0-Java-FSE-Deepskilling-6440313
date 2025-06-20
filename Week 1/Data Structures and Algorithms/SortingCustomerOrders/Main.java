public class Main {
    public static void main(String[] args) {
        Order[] orders = {
            new Order("O001", "Alice", 1500.50),
            new Order("O002", "Bob", 520.00),
            new Order("O003", "Charlie", 2250.75),
            new Order("O004", "Diana", 899.99)
        };

        System.out.println("Original Orders:");
        for (Order o : orders) System.out.println(o);

        // Bubble Sort
        SortUtility.bubbleSort(orders);
        System.out.println("\nSorted by Bubble Sort:");
        for (Order o : orders) System.out.println(o);

        // Reset and Quick Sort
        orders = new Order[] {
            new Order("O001", "Alice", 1500.50),
            new Order("O002", "Bob", 520.00),
            new Order("O003", "Charlie", 2250.75),
            new Order("O004", "Diana", 899.99)
        };

        SortUtility.quickSort(orders, 0, orders.length - 1);
        System.out.println("\nSorted by Quick Sort:");
        for (Order o : orders) System.out.println(o);
    }
}
