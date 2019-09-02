package com.lee.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author Avin lee
 * @Create 2019/8/23 15:10 by IntelliJ IDEA
 * @Description
 */
@Getter
@Setter
@ToString
public class Article {
    private Integer id;
    private String title;
    private String content;
    private Integer user_id;
    private String userAccout;
    private Date create_time;
}
