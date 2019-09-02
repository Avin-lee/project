package com.lee.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Avin lee
 * @Create 2019/8/29 10:59 by IntelliJ IDEA
 * @Description
 */
@Getter
@Setter
public class SystemException extends RuntimeException {

    private String code;

    public SystemException(String message) {
        super("系统异常："+ message);
        code = "501";
    }

    public SystemException(Throwable cause){
        super("系统异常："+ cause);
        code = "501";
    }
}
