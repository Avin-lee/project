package com.cashsystem.entity;

import com.cashsystem.common.AccountStatus;
import com.cashsystem.common.AccountType;
import lombok.Data;  //自动提供当前类的get/set方法等(配套@Data)

/**
 * @Author Avin
 * @Create 2019/8/5 14:58
 * @Description
 */
@Data  //自动提供当前类的get/set方法等
public class Account {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private AccountType accountType;
    private AccountStatus accountStatus;
}
