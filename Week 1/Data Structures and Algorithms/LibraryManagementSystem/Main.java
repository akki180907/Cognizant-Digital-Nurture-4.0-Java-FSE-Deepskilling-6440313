public class Main {
    public static void main(String[] args) {
        Book[] books = {
            new Book("B001", "The Alchemist", "Paulo Coelho"),
            new Book("B002", "1984", "George Orwell"),
            new Book("B003", "To Kill a Mockingbird", "Harper Lee"),
            new Book("B004", "Sapiens", "Yuval Noah Harari")
        };

        System.out.println("Linear Search for '1984':");
        Book foundLinear = SearchUtility.linearSearch(books, "1984");
        System.out.println(foundLinear != null ? foundLinear : "Book not found");

        System.out.println("\nBinary Search for 'Sapiens':");
        Book foundBinary = SearchUtility.binarySearch(books, "Sapiens");
        System.out.println(foundBinary != null ? foundBinary : "Book not found");
    }
}
