package org.example.library.bus;

import org.example.library.algorithm.GenericAlgorithm;
import org.example.library.daos.BookDao;
import org.example.library.models.Book;
import org.example.library.models.Reader;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class BookBus {
    private final BookDao bookDao;

    public BookBus() {
        this.bookDao = new BookDao();
    }


    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    public String generateBookId() {
        return UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    }

    public void createNewBook(Book book) {
        bookDao.save(book);
    }

    public void deleteBook(String id) {
        bookDao.delete(id);
    }

    public List<Book> search(String keyword) {
        return GenericAlgorithm.search(getBooks(), keyword, book ->
                book.getBookName() + " " +
                        book.getBookId() + " " +
                        book.getAuthor() + " " +
                        book.getPublisher() + " " +
                        book.getCategory());
    }

    public List<String> getBookIds() {
        return getBooks().stream()
                .map(Book::getBookId)
                .toList();
    }

    public Book findBookBy(String id) {
        return bookDao.findById(id);
    }

    public void increaseRemaining(String bookId) {
        bookDao.increaseRemaining(bookId);
    }

    public void decreaseRemaining(String bookId) {
        bookDao.decreaseRemaining(bookId);
    }

    public int getRemaining(String bookId) {
        return bookDao.getRemaining(bookId);
    }

}
