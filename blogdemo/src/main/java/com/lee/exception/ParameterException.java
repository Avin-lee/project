package com.lee.exception;


import lombok.Getter;
import lombok.Setter;

/**
 * @Author Avin lee
 * @Create 2019/8/29 10:20 by IntelliJ IDEA
 * @Description
 */

@Getter
@Setter
public class ParameterException extends RuntimeException {

    private String code;

    public ParameterException() {
    }

    public ParameterException(String message) {
        super(message);
    }

    public ParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParameterException(Throwable cause) {
        super(cause);
    }

    public ParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
