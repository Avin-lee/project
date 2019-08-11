package com.cashsystem.cmd.impl;

import com.cashsystem.service.AccountService;
import com.cashsystem.service.GoodsService;
import com.cashsystem.service.OrderService;

/**
 * @Author Avin
 * @Create 2019/8/5 14:46
 * @Description
 */
public abstract class AbstractCommand implements Command{
    //启动所有的服务
    public AccountService accountService;
    public GoodsService goodsService;
    public OrderService orderService;
    public AbstractCommand(){
        this.accountService = new AccountService();
        this.goodsService = new GoodsService();
        this.orderService = new OrderService();
    }
}
