package com.lee.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Avin lee
 * @Create 2019/8/23 17:27 by IntelliJ IDEA
 * @Description
 */
@Getter
@Setter
public class JSON {
    private boolean success;  //操作是否成功
    private String code;
    private String message;
    private Object data;
}
