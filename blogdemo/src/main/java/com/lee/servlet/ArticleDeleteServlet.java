package com.lee.servlet;

import com.lee.exception.BusinessException;
import com.lee.exception.ParameterException;
import com.lee.util.DBUtil;

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

@WebServlet("/articleDelete")
public class ArticleDeleteServlet extends BaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String ids = req.getParameter("ids");
        int[] intIds = null;
        try {
            String[] idsArray = ids.split(",");
            intIds = new int[idsArray.length];
            for (int i = 0; i < idsArray.length; i++) {
                intIds[i] = Integer.parseInt(idsArray[i]);
            }
        }catch (Exception e){
            throw new ParameterException("请求参数错误ids=" + ids);
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        //处理业务及数据库操作
        try {
            connection = DBUtil.getConnection();
            StringBuilder sql = new StringBuilder("delete from article where id in(");
            for(int i = 0;i < intIds.length;i++){
                if(i == 0){
                    sql.append("?");
                }else{
                    sql.append(",?");
                }
            }
            sql.append(")");

            preparedStatement = connection.prepareStatement(sql.toString());

            for(int i = 0;i < intIds.length;i++){
                preparedStatement.setInt(i + 1,intIds[i]);
            }
            int result = preparedStatement.executeUpdate();
            if(result > 0){
                return result;
            }else {
                throw new BusinessException("没有该用户！");
            }
        }finally {
            DBUtil.close(connection,preparedStatement,null);
        }
    }
}
