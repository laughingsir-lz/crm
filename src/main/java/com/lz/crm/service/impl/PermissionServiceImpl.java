package com.lz.crm.service.impl;

import com.lz.crm.domain.Permission;
import com.lz.crm.mapper.PermissionMapper;
import com.lz.crm.query.QueryObject;
import com.lz.crm.service.IPermissionService;
import com.lz.crm.util.PermissionUtil;
import com.lz.crm.util.RequiredPermission;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void delete(Long id) {
        permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void save(Permission permission) {
        permissionMapper.insert(permission);
    }

    @Override
    public Permission get(Long id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Permission> listAll() {
        return permissionMapper.selectAll();
    }

    @Override
    public void update(Permission permission) {
        permissionMapper.updateByPrimaryKey(permission);
    }

    public PageInfo query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Permission> list = permissionMapper.selectForList(qo);
        return new PageInfo(list);
    }

    @Autowired
    private ApplicationContext ctx; //spring上下文对象


    public void reload() {
        //获取数据库中所有的权限表达式
        List<String> allExpression = permissionMapper.selectAllExpression();
        //获取带有某个注解的bean ,获取所有的controller
        Map<String, Object> beans = ctx.getBeansWithAnnotation(Controller.class);
        Collection<Object> controllers = beans.values();
        for (Object controller : controllers) {
            //获取字节码对象
            Class<?> clazz = controller.getClass();
            //获取每一个controller里面的每个方法
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                //判断该方法是否有贴自定义的权限注解
                RequiredPermission annotation = method.getAnnotation(RequiredPermission.class);
                //如果有贴就进行处理
                if (annotation != null) {
                    String expression = PermissionUtil.handlerExpression(method);
                    //注解上直接有表达式,可以直接获取
                    //permission.setExpression(annotation.expression());
                    //如果该权限的表达式已经存在数据库,就不进行插入,如果不存在就插入
                    if (!allExpression.contains(expression)) {
                        Permission permission = new Permission();
                        permission.setName(annotation.name());//权限名称
                        permission.setExpression(expression);
                        permissionMapper.insert(permission);
                    }
                }
                //如果没有贴,就不进行处理
            }
        }


    }

    public List<String> selectByEmpId(Long id) {
        return permissionMapper.selectByEmpId(id);
    }
}
