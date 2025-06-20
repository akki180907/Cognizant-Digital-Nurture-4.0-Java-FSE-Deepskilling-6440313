import java.util.Arrays;
import java.util.Comparator;

public class SearchUtility {

    // Linear Search by Title
    public static Book linearSearch(Book[] books, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // Binary Search by Title
    public static Book binarySearch(Book[] books, String title) {
        Arrays.sort(books, Comparator.comparing(Book::getTitle)); // ensure sorted
        int left = 0, right = books.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compare = books[mid].getTitle().compareToIgnoreCase(title);

            if (compare == 0) return books[mid];
            else if (compare < 0) left = mid + 1;
            else right = mid - 1;
        }
        return null;
    }
}
