package com.lee.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lee.exception.SystemException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @Author Avin lee
 * @Create 2019/8/23 15:30 by IntelliJ IDEA
 * @Description
 */
public class JSONUtil {
    public static String format(Object obj){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try {
            return objectMapper.writeValueAsString(obj);
        }catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new SystemException("JSON解析异常：" + obj);
        }
    }

    public static <T> T get(HttpServletRequest request,Class<T> clazz){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try{
            return objectMapper.readValue(request.getInputStream(),clazz);
        }catch (IOException e){
            e.printStackTrace();
            throw new SystemException("JSON反序列化异常！");
        }
    }
}
