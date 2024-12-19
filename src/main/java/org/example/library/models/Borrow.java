package org.example.library.models;

public class Borrow {
    private String borrowId;
    private String readerId;
    private String bookId;
    private String borrowDate;
    private String returnDate;
    private String dueDate;
    private String readerName;
    private String bookName;
    private String status;

    public Borrow() {
    }

    public Borrow(String borrowId, String readerId, String bookId, String borrowDate, String returnDate, String dueDate, String readerName, String bookName, String status) {
        this.borrowId = borrowId;
        this.readerId = readerId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.dueDate = dueDate;
        this.readerName = readerName;
        this.bookName = bookName;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }

    @Override
    public String toString() {
        return String.join(",", borrowId, readerId, bookId, borrowDate, returnDate, dueDate, readerName, bookName, status);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String borrowId;
        private String readerId;
        private String bookId;
        private String borrowDate;
        private String returnDate;
        private String dueDate;
        private String readerName;
        private String bookName;
        private String status;

        public Builder readerId(String readerId) {
            this.readerId = readerId;
            return this;
        }

        public Builder borrowId(String borrowId) {
            this.borrowId = borrowId;
            return this;
        }

        public Builder bookId(String bookId) {
            this.bookId = bookId;
            return this;
        }

        public Builder borrowDate(String borrowDate) {
            this.borrowDate = borrowDate;
            return this;
        }

        public Builder returnDate(String returnDate) {
            this.returnDate = returnDate;
            return this;
        }

        public Builder dueDate(String dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder readerName(String readerName) {
            this.readerName = readerName;
            return this;
        }

        public Builder bookName(String bookName) {
            this.bookName = bookName;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Borrow build() {
            return new Borrow(borrowId, readerId, bookId, borrowDate, returnDate, dueDate, readerName, bookName, status);
        }
    }
}
