package com.lz.crm.util;

import com.lz.crm.domain.Employee;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

public abstract class UserContext {
    public static final String EMPLOYEE_IN_SESSION = "EMPLOYEE_IN_SESSION";
    public static final String PERMISSION_IN_SESSION = "PERMISSION_IN_SESSION";

    /**
     * 获取session
     *
     * @return
     */
    public static HttpSession getSession() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest().getSession();
    }

    /**
     * 从session中获取当前登录用户
     *
     * @return
     */
    public static Employee getCurrentUser() {
        return (Employee) getSession().getAttribute(EMPLOYEE_IN_SESSION);
    }

    /**
     * 把用户存到session中
     *
     * @param employee
     */
    public static void setCurrentUser(Employee employee) {
        getSession().setAttribute(EMPLOYEE_IN_SESSION, employee);
    }

    /**
     * 从session中获取当前登录用户的权限数据
     *
     * @return
     */
    public static List<String> getPermissions() {
        return (List<String>) getSession().getAttribute(PERMISSION_IN_SESSION);
    }


    /**
     * 把权限存到session中
     *
     * @param permissions
     */
    public static void setPermissions(List<String> permissions) {
        getSession().setAttribute(PERMISSION_IN_SESSION, permissions);
    }
}
