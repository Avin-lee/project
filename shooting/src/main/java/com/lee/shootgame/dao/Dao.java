package com.lee.shootgame.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author Avin lee
 * @Create 2019/8/23 23:00 by IntelliJ IDEA
 * @Description
 */
public class Dao{
    public void save(int score){

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            String sql = "insert into score(score) values(?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,score);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,preparedStatement, null);
        }

    }
}
