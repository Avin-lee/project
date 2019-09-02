package com.lee;

import com.lee.util.DBUtil;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

/**
 * @Author Avin lee
 * @Create 2019/8/23 12:42 by IntelliJ IDEA
 * @Description
 */
public class DBUtilTest {

    @Test
    public void testConnection(){
        Connection connection = DBUtil.getConnection();
        //System.out.println(connection);
        Assert.assertNotNull(connection);
    }
}
