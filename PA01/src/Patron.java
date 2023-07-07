import java.util.ArrayList;
import java.util.List;
class Patron {
    private String name;
    private String libraryCardNumber;
    private List<Book> reservedBooks;

    public Patron(String name, String libraryCardNumber) {
        this.name = name;
        this.libraryCardNumber = libraryCardNumber;
        this.reservedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getLibraryCardNumber() {
        return libraryCardNumber;
    }

    public List<Book> getReservedBooks() {
        return reservedBooks;
    }

    public void reserveBook(Library library, String query) {
        List<Book> searchResults = library.searchBooks(query);

        if (!searchResults.isEmpty()) {
            Book book = searchResults.get(0);
            library.reserveBook(book);
            reservedBooks.add(book);
        }
    }
}