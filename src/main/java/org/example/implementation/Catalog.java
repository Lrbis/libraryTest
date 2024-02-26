package org.example.implementation;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Catalog {

    private Set<Book> books;

    public Catalog(Book... books) {
        this.books = new HashSet<>();
        this.books.addAll(List.of(books));
    }

    public Catalog(Collection<Book> books) {
        this.books = (HashSet<Book>) books;
    }

    public Catalog() {
        this.books = new HashSet<>();
    }

    /**
     * Adds a book to the catalog and returns true if the book was successfully added. Returns false otherwise.
     *
     * @param book Book to add.
     * @return True if the book was added.
     */
    public boolean addBook(Book book) {
    }

    /**
     * Removes a book from the catalog, returns true if the book was successfully removed, false otherwise.
     *
     * @param book Book to remove.
     * @return True if the book was sucessfully removed.
     */
    public boolean removeBook(Book book) {
    }

    /**
     * Returns all books that are available.
     */
    public Collection<Book> listAvailable() {
    }

    public Collection<Book> searchByAuthor(String authorName) {
    }

    public Book searchByISBN(int ISBN) {
    }

    public Book searchByTitle(String title) {
    }

    public Collection<Book> searchByGenre(Genre genre) {
    }

    /**
     * Tries to rent a book out of the catalog, based on the provided title. The book must be available.
     * If the book is available, returns true and print "Book successfully rented."
     *
     * If the book is not available, returns false and prints "Book is already rented."
     * @param title Title of the book to rent.
     * @return True if book was sucessfully rented.
     */
    public boolean borrowBookByTitle(String title) {

    }

    /**
     * Tries to rent a book out of the catalog, based on the provided ISBN. The book must be available.
     * If the book is available, returns true and print "Book successfully rented."
     *
     * If the book is not available, returns false and prints "Book is already rented."
     * @param ISBN ISBN of the book to rent.
     * @return True if book was sucessfully rented.
     */
    public boolean borrowBookByISBN(int ISBN) {

    }

}
