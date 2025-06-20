public class Book {
    String bookId;
    String title;
    String author;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String toString() {
        return "[" + bookId + "] " + title + " by " + author;
    }
}
