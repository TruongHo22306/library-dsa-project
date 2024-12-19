package org.example.library.daos;

import org.example.library.common.FilePath;
import org.example.library.models.Account;
import org.example.library.utility.FileUtil;

import java.util.List;

public class AccountDao {
    private List<Account> accounts;

    public AccountDao() {
        accounts = FileUtil.readFromFile(FilePath.ACCOUNT_FILE, line -> {
            String[] parts = line.split(",");
            return new Account(parts[0], parts[1]);
        });

    }

    public boolean authenticate(String username, String password) {
        return accounts.stream()
                .anyMatch(account -> account.getUsername().equals(username) && account.getPassword().equals(password));
    }

    public void changePassword(String username, String password) {
        accounts.stream()
                .filter(account -> account.getUsername().equals(username))
                .forEach(account -> account.setPassword(password));
        FileUtil.writeToFile(FilePath.ACCOUNT_FILE, accounts);
    }
}


