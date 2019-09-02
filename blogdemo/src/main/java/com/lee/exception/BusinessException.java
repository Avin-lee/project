package com.lee.exception;

/**
 * @Author Avin lee
 * @Create 2019/8/29 15:08 by IntelliJ IDEA
 * @Description
 */
public class BusinessException extends RuntimeException {
    private String code;
    public BusinessException(String message) {
        super("业务异常：："+ message);
        code = "401";
    }

    public BusinessException(Throwable cause){
        super("业务异常："+ cause);
        code = "401";
    }
}
