package com.lee.servlet;

import com.lee.entity.JSON;
import com.lee.exception.ParameterException;
import com.lee.exception.SystemException;
import com.lee.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Avin lee
 * @Create 2019/8/23 17:09 by IntelliJ IDEA
 * @Description
 */
public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        JSON result = new JSON();
        try {
            Object data = process(req, resp);
            result.setSuccess(true);
            result.setCode("200");
            result.setMessage("操作成功");
            result.setData(data);
        }catch (Exception e){
            if (e instanceof ParameterException){
                result.setCode(((ParameterException)e).getCode());
                result.setMessage(((ParameterException)e).getMessage());
            } else if (e instanceof SystemException) {
                result.setCode(((SystemException)e).getCode());
                result.setMessage(((SystemException)e).getMessage());
            } else {
                e.printStackTrace();
                result.setCode("500");
                result.setMessage("服务器异常");
            }
        }
        resp.getWriter().write(JSONUtil.format(result));
    }

    public abstract Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
