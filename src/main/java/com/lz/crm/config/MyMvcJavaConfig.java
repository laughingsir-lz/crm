package com.lz.crm.config;

import com.lz.crm.web.interceptor.LoginInterceptor;
import com.lz.crm.web.interceptor.PermissionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.transform.impl.AddInitTransformer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zzz
 * @date 2020/9/4  14:37
 */
@Configuration
public class MyMvcJavaConfig implements WebMvcConfigurer {
    // 通过spring管理 直接注入相应的bean
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private PermissionInterceptor permissionInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 传入对象
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")  // 要拦截的路径
                .excludePathPatterns("/login.html","/loginUser.do","/css/**","/js/**"); // 排除拦截

        registry.addInterceptor(permissionInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/loginUser.do","/login.html","/css/**","/js/**");
    }
}
