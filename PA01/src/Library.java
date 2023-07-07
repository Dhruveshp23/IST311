import java.util.ArrayList;
import java.util.List;
class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> searchBooks(String query) {
        List<Book> searchResults = new ArrayList<>();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                searchResults.add(book);
            }
        }

        return searchResults;
    }

    public void reserveBook(Book book) {
        if (!book.isCheckedOut()) {
            book.setCheckedOut(true);
            if (book instanceof PhysicalBook) {
                ((PhysicalBook) book).setDueDate("2023-06-15");
            }
        }
    }
}