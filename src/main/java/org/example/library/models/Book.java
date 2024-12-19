package org.example.library.models;

import java.time.LocalDate;

public class Book {
    private String bookId;
    private String bookName;
    private String publisher;
    private String category;
    private String author;
    private String publishDate;
    private int remaining;
    private String image;

    public Book() {
    }

    public Book(String bookId, String bookName, String publisher, String category, String author, String publishDate, int remaining, String image) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.publisher = publisher;
        this.category = category;
        this.author = author;
        this.publishDate = publishDate;
        this.remaining = remaining;
        this.image = image;
    }

    public String getBookId() {
        return bookId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    @Override
    public String toString() {
        return String.join(",", bookId, bookName, publisher, category, author, publishDate, String.valueOf(remaining), image);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String bookId;
        private String bookName;
        private String publisher;
        private String category;
        private String author;
        private String publishDate;
        private int remaining;
        private String image;

        public Builder bookId(String bookId) {
            this.bookId = bookId;
            return this;
        }

        public Builder bookName(String bookName) {
            this.bookName = bookName;
            return this;
        }

        public Builder publisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder publishDate(String publishDate) {
            this.publishDate = publishDate;
            return this;
        }

        public Builder remaining(int remaining) {
            this.remaining = remaining;
            return this;
        }

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        public Book build() {
            return new Book(bookId, bookName, publisher, category, author, publishDate, remaining, image);
        }
    }
}
