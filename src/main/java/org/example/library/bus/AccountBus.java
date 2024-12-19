package org.example.library.bus;

import org.example.library.daos.AccountDao;

public class AccountBus {
    private final AccountDao accountDao;

    public AccountBus() {
        accountDao = new AccountDao();
    }

    public boolean authenticate(String username, String password) {
        return accountDao.authenticate(username, password);
    }

    public void changePassword(String username, String password) {
        accountDao.changePassword(username, password);
    }
}
