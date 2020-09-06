package com.lz.crm.web.interceptor;

import com.lz.crm.domain.Employee;
import com.lz.crm.exception.UnauthorizedException;
import com.lz.crm.util.PermissionUtil;
import com.lz.crm.util.RequiredPermission;
import com.lz.crm.util.UserContext;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class PermissionInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, ServletException {
        // 获取当前登录用户
        Employee employee = UserContext.getCurrentUser();
        // 判断是否是超管
        if (employee.isAdmin()) {
            return true;//放行
        }
        //获取当前执行的方法,handler强转为HandlerMethod的真实类型
        //departmentcontroller.list
        HandlerMethod method = (HandlerMethod) handler;

        //需要判断现在执行的方法是否有贴@RequiredPermission注解
        RequiredPermission annotation = method.getMethodAnnotation(RequiredPermission.class);
        if (annotation == null) { //没有贴注解,代表不需要验证,直接放行
            return true;
        }

        //如果有,获取当前登录用户的权限
        List<String> permissions = UserContext.getPermissions();
        //注意:加载权限时,用哪些方式生成表达式,现在就要用哪些
        String expression = PermissionUtil.handlerExpression(method.getMethod());

        //需要进行权限验证(判断当前用户是否有该权限,如果有就放行,如果没有就拒绝访问)
        if (permissions.contains(expression)) {
            return true;
        }
        throw new UnauthorizedException();
    }
}
