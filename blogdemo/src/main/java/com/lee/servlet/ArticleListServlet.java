package com.lee.servlet;

import com.lee.entity.Article;
import com.lee.exception.ParameterException;
import com.lee.util.DBUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Avin lee
 * @Create 2019/8/23 14:12 by IntelliJ IDEA
 * @Description
 */

@WebServlet("/articleList")
public class ArticleListServlet extends BaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Article> articleList = new ArrayList<>();

        //处理前端请求数据
        String sid = req.getParameter("id");
        try{
            Integer id = Integer.parseInt(sid);
        }catch (Exception e){
            throw new ParameterException("id错误  ("+sid+")");
        }

        //处理业务及数据库操作
        try {
            connection = DBUtil.getConnection();
            String sql = "select a.id,a.title,a.content,a.create_time from article a,user u where a.user_id=u.id and u.id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(req.getParameter("id")));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Article article = new Article();
                article.setId(resultSet.getInt("id"));
                article.setTitle(resultSet.getString("title"));
                article.setContent(resultSet.getString("content"));
                article.setCreate_time(resultSet.getTime("create_time"));
                articleList.add(article);
            }
        }finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }
        return articleList;
    }
}
