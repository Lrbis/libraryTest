import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.example.implementation.Book;
import org.example.implementation.Catalog;
import org.example.implementation.Genre;
import org.junit.Test;

public class testClass {

    @Test
    public void testBook() {
        Book newBook = new Book(123097, "Test title", "test author", Genre.FANTASY, true);

        assertEquals("Test title", newBook.getTitle());
        assertEquals(123097, newBook.getISBN());
        assertEquals("test author", newBook.getAuthor());
        assertEquals(Genre.FANTASY, newBook.getGenre());
    }


    /**
     * Can not enter two books with the same ISBN into the catalog. Requires changing equals and hashCode in Book class.
     */
    @Test
    public void testCatalog() {
        Book newBook = new Book(123097, "Test title", "test author", Genre.FANTASY, true);
        Book newBook2 = new Book(123097, "Test title", "test author", Genre.FANTASY, true);

        Catalog catalog = new Catalog();

        assertTrue(catalog.addBook(newBook));
        assertFalse(catalog.addBook(newBook2));
    }

    @Test
    public void testSetAvailability() {
        Book book = new Book(1234567890, "The Great Gatsby", "F. Scott Fitzgerald", Genre.HISTORY, true);

        assertTrue(book.isAvailable());
        book.setAvailable(false);
        assertFalse(book.isAvailable());
    }

    @Test
    public void testSetISBN() {
        Book book = new Book(1234567890, "The Great Gatsby", "F. Scott Fitzgerald", Genre.HISTORY, true);

        assertEquals(1234567890, book.getISBN());
        book.setISBN(987654321);
        assertEquals(987654321, book.getISBN());
    }

    @Test
    public void testSetGenre() {
        Book book = new Book(1234567890, "The Great Gatsby", "F. Scott Fitzgerald", Genre.HISTORY, true);

        assertEquals(Genre.HISTORY, book.getGenre());
        book.setGenre(Genre.POETRY);
        assertEquals(Genre.POETRY, book.getGenre());
    }


    /*
     * Catalog testing
     */

    @Test
    public void testAddAndRemoveBook() {
        Catalog catalog = new Catalog();
        Book book1 = new Book(1234567890, "The Great Gatsby", "F. Scott Fitzgerald", Genre.FICTION, true);
        Book book2 = new Book(987654321, "To Kill a Mockingbird", "Harper Lee", Genre.FICTION, true);
        Book book3 = new Book(987654324, "To Kill a Mockingbird", "Harper Lee", Genre.FICTION, false);

        assertTrue(catalog.addBook(book1));
        assertTrue(catalog.addBook(book2));
        assertTrue(catalog.addBook(book3));

        assertEquals(2, catalog.listAvailable().size());

        assertTrue(catalog.removeBook(book1));
        assertEquals(1, catalog.listAvailable().size());

        assertFalse(catalog.removeBook(book1)); // Removing again should return false
    }

    @Test
    public void testSearchByAuthor() {
        Catalog catalog = new Catalog();
        Book book1 = new Book(1234567890, "The Great Gatsby", "F. Scott Fitzgerald", Genre.FICTION, true);
        Book book2 = new Book(987654321, "To Kill a Mockingbird", "Harper Lee", Genre.FICTION, false);
        catalog.addBook(book1);
        catalog.addBook(book2);

        Collection<Book> result = catalog.searchByAuthor("F. Scott Fitzgerald");
        assertEquals(1, result.size());
        assertTrue(result.contains(book1));
    }

    @Test
    public void testSearchByISBN() {
        Catalog catalog = new Catalog();
        Book book1 = new Book(1234567890, "The Great Gatsby", "F. Scott Fitzgerald", Genre.FICTION, true);
        Book book2 = new Book(987654321, "To Kill a Mockingbird", "Harper Lee", Genre.FICTION, false);
        catalog.addBook(book1);
        catalog.addBook(book2);

        assertEquals(book1, catalog.searchByISBN(1234567890));
        assertNull(catalog.searchByISBN(1111111111)); // Non-existent ISBN
    }

    @Test
    public void testSearchByTitle() {
        Catalog catalog = new Catalog();
        Book book1 = new Book(1234567890, "The Great Gatsby", "F. Scott Fitzgerald", Genre.FICTION, true);
        Book book2 = new Book(987654321, "To Kill a Mockingbird", "Harper Lee", Genre.FICTION, false);
        catalog.addBook(book1);
        catalog.addBook(book2);

        assertEquals(book2, catalog.searchByTitle("To Kill a Mockingbird"));
        assertNull(catalog.searchByTitle("Moby Dick")); // Non-existent title
    }

    @Test
    public void testSearchByGenre() {
        Catalog catalog = new Catalog();
        Book book1 = new Book(1234567890, "The Great Gatsby", "F. Scott Fitzgerald", Genre.FICTION, true);
        Book book2 = new Book(987654321, "To Kill a Mockingbird", "Harper Lee", Genre.FICTION, false);
        catalog.addBook(book1);
        catalog.addBook(book2);

        Collection<Book> result = catalog.searchByGenre(Genre.FICTION);
        assertEquals(2, result.size());
        assertTrue(result.containsAll(Arrays.asList(book1, book2)));
    }

    @Test
    public void testBorrowBookByTitle() {
        Catalog catalog = new Catalog();
        Book book1 = new Book(1234567890, "The Great Gatsby", "F. Scott Fitzgerald", Genre.FICTION, true);
        catalog.addBook(book1);

        assertTrue(catalog.borrowBookByTitle("The Great Gatsby"));
        assertFalse(catalog.borrowBookByTitle("The Great Gatsby")); // Already borrowed
    }

    @Test
    public void testBorrowBookByISBN() {
        Catalog catalog = new Catalog();
        Book book1 = new Book(1234567890, "The Great Gatsby", "F. Scott Fitzgerald", Genre.FICTION, true);
        catalog.addBook(book1);

        assertTrue(catalog.borrowBookByISBN(1234567890));
        assertFalse(catalog.borrowBookByISBN(1234567890)); // Already borrowed
    }
}
