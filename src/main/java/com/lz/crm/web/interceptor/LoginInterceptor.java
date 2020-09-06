package com.lz.crm.web.interceptor;

import com.lz.crm.util.UserContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        //从session中获取登录用户
        Object employee = UserContext.getCurrentUser();
        if (employee == null) { //没有登录
            response.sendRedirect("/login.html");
            return false;//不放行
        }
        return true; //已经登录,可以放行
    }
}
