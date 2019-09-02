package com.lee.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author Avin lee
 * @Create 2019/8/23 15:05 by IntelliJ IDEA
 * @Description
 */
@Getter
@Setter
@ToString
public class User {
    private Integer id;
    private String name;
    private Date create_time;
}
