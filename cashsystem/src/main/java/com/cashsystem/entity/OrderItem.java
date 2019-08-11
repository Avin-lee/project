package com.cashsystem.entity;

import lombok.Data;

/**
 * @Author Avin
 * @Create 2019/8/5 14:59
 * @Description
 */
@Data
public class OrderItem {
    private Integer id;
    private Integer goods_id;
    private String order_id;
    private String goods_name;
    private String goods_introduce;
    private Integer goods_num;
    private String goods_unit;
    private Integer goods_price;
    private Integer goods_discount;
}
