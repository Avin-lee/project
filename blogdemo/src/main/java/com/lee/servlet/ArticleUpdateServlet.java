package com.lee.servlet;

import com.lee.entity.Article;
import com.lee.exception.BusinessException;
import com.lee.util.DBUtil;
import com.lee.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @Author Avin lee
 * @Create 2019/8/23 14:12 by IntelliJ IDEA
 * @Description
 */

@WebServlet("/articleUpdate")
public class ArticleUpdateServlet extends BaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Connection connection = null;
        PreparedStatement preparedStatement = null;


        //获取JSON类型的请求数据
        Article article = JSONUtil.get(req,Article.class);


        //处理业务及数据库操作
        try {
            connection = DBUtil.getConnection();
            String sql = "update article set title=?,content=? where id  = ?;";
            ;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,article.getTitle());
            preparedStatement.setString(2,article.getContent());
            preparedStatement.setInt(3,article.getId());

            int result = preparedStatement.executeUpdate();

            if(result > 0){
                return result;
            }else {
                throw new BusinessException("没有该文章：" + article.getId());
            }
        }finally {
            DBUtil.close(connection,preparedStatement,null);
        }
    }
}
