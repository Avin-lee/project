package com.lee.servlet;

import com.lee.util.MyActionEnter;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Avin lee
 * @Create 2019/8/31 11:04 by IntelliJ IDEA
 * @Description
 */
@WebServlet("/ueditor")
public class UEditorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = UEditorServlet.class.getClassLoader().getResource("config.json").getPath();
        MyActionEnter actionEnter = new MyActionEnter(req,path);
        String exec = actionEnter.exec();
        resp.getWriter().write(exec);
    }
}
