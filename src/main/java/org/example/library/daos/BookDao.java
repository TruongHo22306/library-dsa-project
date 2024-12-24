package org.example.library.daos;

import org.example.library.algorithm.GenericAlgorithm;
import org.example.library.common.FilePath;
import org.example.library.models.Book;
import org.example.library.utility.FileUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookDao {
    private final List<Book> books;

    public BookDao() {
        this.books = FileUtil.readFromFile(FilePath.BOOK_FILE, line -> {
            String[] parts = line.split(",");
            return new Book(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], Integer.parseInt(parts[6]), parts[7]);
        });
    }

    public List<Book> getBooks() {
        books.clear();
        books.addAll(FileUtil.readFromFile(FilePath.BOOK_FILE, line -> {
            String[] parts = line.split(",");
            return new Book(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], Integer.parseInt(parts[6]), parts[7]);
        }));

        return books;
    }

    public void save(Book book) {
        Book existingBook = findById(book.getBookId());
        if (existingBook != null) {
            existingBook.setRemaining(book.getRemaining());
            existingBook.setImage(book.getImage());
            existingBook.setBookName(book.getBookName());
            existingBook.setPublisher(book.getPublisher());
            existingBook.setCategory(book.getCategory());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setPublishDate(book.getPublishDate());

            books.removeIf(b -> b.getBookId().equals(book.getBookId()));
            books.add(existingBook);

            FileUtil.writeToFile(FilePath.BOOK_FILE, books);

            return;
        }

        books.add(book);
        FileUtil.writeToFile(FilePath.BOOK_FILE, books);
    }

    public void delete(String id) {
        books.removeIf(book -> book.getBookId().equals(id));
        FileUtil.writeToFile(FilePath.BOOK_FILE, books);
    }

    public Book findById(String id) {
        List<Book> sortedBooks = new ArrayList<>(books);

        //sortedBooks.sort(Comparator.comparing(Book::getBookId));

        List<Book> result = GenericAlgorithm.search(sortedBooks, id, Book::getBookId);

        return result.isEmpty() ? null : result.get(0);
    }

    public void update(Book bookUpdate) {
        Book existingBook = findById(bookUpdate.getBookId());

        if (existingBook != null) {
            existingBook.setBookName(bookUpdate.getBookName());
            existingBook.setPublisher(bookUpdate.getPublisher());
            existingBook.setCategory(bookUpdate.getCategory());
            existingBook.setAuthor(bookUpdate.getAuthor());
            existingBook.setPublishDate(bookUpdate.getPublishDate());
        }

        books.removeIf(book -> book.getBookId().equals(bookUpdate.getBookId()));
        books.add(existingBook);
        FileUtil.writeToFile(FilePath.BOOK_FILE, books);
    }


    public void increaseRemaining(String bookId) {
        Book existingBook = findById(bookId);
        if (existingBook != null) {
            existingBook.setRemaining(existingBook.getRemaining() + 1);
            update(existingBook);
        }
    }

    public void decreaseRemaining(String bookId) {
        Book existingBook = findById(bookId);
        if (existingBook != null) {
            existingBook.setRemaining(existingBook.getRemaining() - 1);
            update(existingBook);
        }
    }

    public int getRemaining(String bookId) {
        Book existingBook = findById(bookId);
        return existingBook != null ? existingBook.getRemaining() : 0;
    }
}
