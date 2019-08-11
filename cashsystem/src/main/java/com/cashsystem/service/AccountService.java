package com.cashsystem.service;

import com.cashsystem.dao.AccountDao;
import com.cashsystem.entity.Account;

/**
 * @Author Avin
 * @Create 2019/8/5 14:59
 * @Description
 */
public class AccountService {
    private AccountDao accountDao;

    public AccountService() {
        this.accountDao = new AccountDao();
    }

    public Account login(String username, String password) {
        return this.accountDao.login(username,password);
    }

    public boolean Register(Account account) {
        return this.accountDao.Register(account);
    }
}
