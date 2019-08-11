package com.cashsystem.common;

import lombok.Getter;
import lombok.ToString;

/**
 * @Author Avin
 * @Create 2019/8/5 14:57
 * @Description
 */
@Getter
@ToString
public enum AccountStatus {
    UNLOCK(1,"启用"),LOCK(2,"启停");
    private int flg;
    private String desc;

    AccountStatus(int flg,String desc) {
        this.flg = flg;
        this.desc = desc;
    }

    public static AccountStatus valueof(int flg) {
        for (AccountStatus accountStatus: values()) {
            if(accountStatus.flg == flg) {
                return accountStatus;
            }
        }
        throw new RuntimeException("accountStatus flg"
                + flg + "not fount!");
    }
}
