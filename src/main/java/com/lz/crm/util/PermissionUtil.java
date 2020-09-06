package com.lz.crm.util;

import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

public abstract class PermissionUtil {
    /**
     * 根据method来生成权限表达式
     *
     * @param method
     * @return
     */
    public static String handlerExpression(Method method) {
        String simpleName = method.getDeclaringClass().getSimpleName(); //DeparmentController
        simpleName = simpleName.replace("Controller", "");//Deparment
        simpleName = StringUtils.uncapitalize(simpleName); //把首字母转为小写 deparment
        String methodName = method.getName(); // list
        String expression = simpleName + ":" + methodName; //department:list
        return expression;
    }
}
