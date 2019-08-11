package com.cashsystem.cmd.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Avin
 * @Create 2019/8/5 14:44
 * @Description
 */
@Retention(RetentionPolicy.RUNTIME)  //运用反射来获取类的注解
@Target(ElementType.TYPE)  //表示注解运行的范围--类上
public @interface CommandMeta {
    String name();  //DL
    String desc();  //登录
    String group();  //入口命令
}
