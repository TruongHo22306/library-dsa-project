package org.example.library.bus;

import org.example.library.algorithm.GenericAlgorithm;
import org.example.library.daos.BorrowDao;
import org.example.library.models.Borrow;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class BorrowBus {
    private final BorrowDao borrowDao;

    public BorrowBus() {
        borrowDao = new BorrowDao();
    }

    public void saveBorrow(Borrow borrow) {
        borrowDao.save(borrow);
    }

    public void deleteBorrow(String borrowId) {
        borrowDao.delete(borrowId);
    }

    public List<Borrow> getAllBorrows() {
        return borrowDao.getBorrows();
    }

    public List<Borrow> getAllBorrowsNotReturned() {
        return getAllBorrows().stream()
                .filter(borrow -> borrow.getReturnDate().equals("null"))
                .toList();
    }

    public void updateBorrow(Borrow borrow) {
        borrowDao.update(borrow);
    }

    public String generateBorrowId() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public List<Borrow> getBorrowsLate() {
        return getAllBorrows().stream()
                .filter(borrow -> borrow.getReturnDate().equals("null") && LocalDate.now().isAfter(LocalDate.parse(borrow.getDueDate())))
                .toList();
    }


    public List<Borrow> getBorrowsReturned() {
        return getAllBorrows().stream()
                .filter(borrow -> !borrow.getReturnDate().equals("null"))
                .toList();
    }

    public List<Borrow> search(String keyword) {
        return GenericAlgorithm.search(getAllBorrows(), keyword, borrow ->
                borrow.getBorrowId() + " " +
                        borrow.getBookId() + " " +
                        borrow.getReaderId() + " " +
                        borrow.getBorrowDate() + " " +
                        borrow.getDueDate() + " " +
                        borrow.getReturnDate());
    }

    public List<Borrow> filterBorrowReturnedByReaderId(String readerId) {
        return getBorrowsReturned().stream()
                .filter(borrow -> borrow.getReaderId().equals(readerId))
                .toList();
    }

    public boolean isBorrowed(String bookId, String readerId) {
        return getAllBorrowsNotReturned().stream()
                .anyMatch(borrow -> borrow.getBookId().equals(bookId) && borrow.getReaderId().equals(readerId) && borrow.getReturnDate().equals("null"));
    }

    public int getQuantityNotReturnedByReaderId(String readerId) {
        return (int) getAllBorrowsNotReturned().stream()
                .filter(borrow -> borrow.getReaderId().equals(readerId) && borrow.getReturnDate().equals("null"))
                .count();
    }
}
