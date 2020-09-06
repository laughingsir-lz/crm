package com.lz.crm.exception;

import com.lz.crm.util.JsonResult;
import com.alibaba.fastjson.JSON;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 对控制器进行增强处理
 * @author zzz
 */
@ControllerAdvice
public class CRMExceptionHandler {

    /**
     * 捕获运行过程中出现的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public String handleException(Exception ex, Model model, HandlerMethod handlerMethod, HttpServletResponse response) throws ServletException, IOException {
        //把异常信息打印出,方便我们开发的时候找bug
        ex.printStackTrace();
        //判断当前执行的方法是否是ajax的方法(@ResponseBody)
        if (handlerMethod.hasMethodAnnotation(ResponseBody.class)) {
            //如果是,则返回jsonresult
            JsonResult json = new JsonResult(false, "操作失败,请联系管理员!");
            //使用response对象输出数据(json字符串)
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(json));
            return null;
        }
        //如果不是,则返回错误视图
        model.addAttribute("ex", ex);
        return "common/error"; //返回的错误视图
    }

    /***
     * 处理没有权限的异常
     * @param ex
     * @param model
     * @param handlerMethod
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
   @ExceptionHandler(UnauthorizedException.class)
    public String handleUnauthorizedException(Exception ex, Model model, HandlerMethod handlerMethod, HttpServletResponse response) throws ServletException, IOException {
        //把异常信息打印出,方便我们开发的时候找bug
        ex.printStackTrace();
        //判断当前执行的方法是否是ajax的方法(@ResponseBody)
        if(handlerMethod.hasMethodAnnotation(ResponseBody.class)){
            //如果是,则返回jsonresult
            JsonResult json = new JsonResult(false,"您没有该权限,请联系管理员!");
            //使用response对象输出数据(json字符串)
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(json));
            return null;
        }
        //如果不是,则返回错误视图
        model.addAttribute("ex", ex);
        return "common/nopermission"; //返回的错误视图
    }
}
