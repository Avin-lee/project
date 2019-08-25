package com.lee.shootgame.dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author Avin lee
 * @Create 2019/8/23 22:16 by IntelliJ IDEA
 * @Description
 */
public class DBUtil {

    private static volatile DataSource DATASOURCE;

    private static final String URL = "jdbc:mysql://localhost:3306/shootgame";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "a12345";

    private DBUtil(){

    }

    public static DataSource getDATASOURCE(){
        if(DATASOURCE == null){
            synchronized (DBUtil.class){
                if(DATASOURCE == null){
                    DATASOURCE = new MysqlDataSource();
                    ((MysqlDataSource) DATASOURCE).setURL(URL);
                    ((MysqlDataSource) DATASOURCE).setUser(USER_NAME);
                    ((MysqlDataSource) DATASOURCE).setPassword(PASSWORD);
                }
            }
        }
        return DATASOURCE;
    }

    public static Connection getConnection(){

        try {
            return getDATASOURCE().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
