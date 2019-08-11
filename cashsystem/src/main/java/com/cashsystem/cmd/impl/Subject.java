package com.cashsystem.cmd.impl;

import com.cashsystem.entity.Account;

/**
 * @Author Avin
 * @Create 2019/8/5 14:47
 * @Description
 */
public class Subject {
    private Account account; //账户
    public void setAccount(Account account){
        this.account = account;
    }
    public Account getAccount(){
        return this.account;
    }
}
