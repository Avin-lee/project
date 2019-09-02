package com.lee;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lee.entity.Article;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author Avin lee
 * @Create 2019/8/23 15:34 by IntelliJ IDEA
 * @Description
 */
public class jacksonTest {
    @Test
    public void testJackson() {
        List<Article> articles = new ArrayList<>();
        Article article = new Article();
        article.setId(1);
        article.setTitle("我的博客");
        article.setContent("内容");
        article.setCreate_time(new Date());
        articles.add(article);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try {
            String result = objectMapper.writeValueAsString(articles);
            System.out.println(result);
        }catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
