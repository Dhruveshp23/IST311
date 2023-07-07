import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LibraryTests {

    @Test
    public void testSearchBooks() {
        Library library = new Library();

        PhysicalBook book1 = new PhysicalBook("The Enigma of Shadows", "John Doe");
        PhysicalBook book2 = new PhysicalBook("The Hidden Key", "Jane Smith");
        DigitalBook book3 = new DigitalBook("The Secret Code", "James Johnson");

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        List<Book> searchResults = library.searchBooks("Enigma");
        Assertions.assertEquals(1, searchResults.size());
        Assertions.assertEquals("The Enigma of Shadows", searchResults.get(0).getTitle());
    }

    @Test
    public void testReserveBook() {
        Library library = new Library();

        PhysicalBook book1 = new PhysicalBook("The Enigma of Shadows", "John Doe");
        library.addBook(book1);

        library.reserveBook(book1);
        Assertions.assertTrue(book1.isCheckedOut());
        Assertions.assertNotNull(book1.getDueDate());
    }
}
