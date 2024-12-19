package org.example.library.daos;

import org.example.library.common.FilePath;
import org.example.library.models.Borrow;
import org.example.library.utility.FileUtil;

import java.util.List;

public class BorrowDao {
    private final List<Borrow> borrows;

    public BorrowDao() {
        borrows = FileUtil.readFromFile(FilePath.BORROW_FILE, line -> {
            String[] parts = line.split(",");
            return new Borrow(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8]);
        });
    }

    public List<Borrow> getBorrows() {
        borrows.clear();
        borrows.addAll(FileUtil.readFromFile(FilePath.BORROW_FILE, line -> {
            String[] parts = line.split(",");
            return new Borrow(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8]);
        }));

        return borrows;
    }



    public void save(Borrow borrow) {
        borrows.add(borrow);
        FileUtil.writeToFile(FilePath.BORROW_FILE, borrows);
    }

    public void update(Borrow borrow) {
        borrows.removeIf(b -> b.getBorrowId().equals(borrow.getBorrowId()));
        borrows.add(borrow);
        FileUtil.writeToFile(FilePath.BORROW_FILE, borrows);
    }

    public void delete(String borrowId) {
        borrows.removeIf(borrow -> borrow.getBorrowId().equals(borrowId));
        FileUtil.writeToFile(FilePath.BORROW_FILE, borrows);
    }

}
