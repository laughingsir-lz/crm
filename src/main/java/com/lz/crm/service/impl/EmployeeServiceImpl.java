package com.lz.crm.service.impl;

import com.lz.crm.domain.Employee;
import com.lz.crm.mapper.EmployeeMapper;
import com.lz.crm.query.QueryObject;
import com.lz.crm.service.IEmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public void delete(Long id) {
        employeeMapper.deleteByPrimaryKey(id);
        //删除关系
        employeeMapper.deleteRelation(id);
    }

    @Override
    public void save(Employee employee, Long[] ids) {
        String password = employee.getPassword();
        //对密码进行加密(用户名作为盐)
        /*Md5Hash md5Hash = new Md5Hash(password,employee.getName());
        //设置加密的结果
        employee.setPassword(md5Hash.toString());*/
        employeeMapper.insert(employee);
        //处理中间表的关系
        if (ids != null && ids.length > 0) {
            for (Long rid : ids) {
                employeeMapper.insertRelation(employee.getId(), rid);
            }
        }
    }

    @Override
    public Employee get(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> listAll() {
        return employeeMapper.selectAll();
    }

    @Override
    public void update(Employee employee, Long[] ids) {
        employeeMapper.updateByPrimaryKey(employee);
        //删除关系
        employeeMapper.deleteRelation(employee.getId());
        //处理中间表的关系
        if (ids != null && ids.length > 0) {
            for (Long rid : ids) {
                employeeMapper.insertRelation(employee.getId(), rid);
            }
        }


    }

    @Override
    public Employee login(String username, String password) {
        Employee employee = employeeMapper.selectByUsernameAndPassword(username, password);
        //判断用户是否存在
        if (employee == null) {
            throw new RuntimeException("账号和密码不存在");
        }
        return employee;
    }

    public PageInfo query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Employee> list = employeeMapper.selectForList(qo);
        return new PageInfo(list);
    }

    public void batchDelete(Long[] ids) {
        employeeMapper.deleteBatch(ids);
    }

    public Employee selectByName(String name) {
        return employeeMapper.selectByName(name);
    }

    public Workbook exportXls() {
        //创建excel文件
        Workbook wb = new HSSFWorkbook();
        //创建sheet(一页纸)
        Sheet sheet = wb.createSheet("员工名单");
        //标题行
        Row row = sheet.createRow(0);
        //设置内容到单元格中
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("邮箱");
        row.createCell(2).setCellValue("年龄");
        //查询员工数据
        List<Employee> employees = employeeMapper.selectAll();
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            //创建行(每个员工就是一行)
            row = sheet.createRow(i + 1);
            //设置内容到单元格中
            row.createCell(0).setCellValue(employee.getName());
            row.createCell(1).setCellValue(employee.getEmail());
            row.createCell(2).setCellValue(employee.getAge());
        }
        return wb;
    }


    public void importXls(MultipartFile file) throws Exception {
        //把接收到的文件以excel的方式去读取并操作
        Workbook wb = new HSSFWorkbook(file.getInputStream());
        //读取第一页
        Sheet sheet = wb.getSheetAt(0);
        //获取行的最大索引数
        int lastRowNum = sheet.getLastRowNum();
        //从索引为1的行数开始读(忽略标题行)
        for (int i = 1; i <= lastRowNum; i++) {
            //获取行数据
            Row row = sheet.getRow(i);
            Employee employee = new Employee();
            employee.setName(row.getCell(0).getStringCellValue());
            employee.setEmail(row.getCell(1).getStringCellValue());
            //获取文本格式的单元格内容
            employee.setAge(Integer.valueOf(row.getCell(2).getStringCellValue()));
            //获取数值类型的单元格内容
            //double value = row.getCell(2).getNumericCellValue();
            //employee.setAge((int)value);
            //设置默认密码1
            employee.setPassword("1");
            //调用保存方法
            this.save(employee, null);
        }

    }

    public List<Employee> selectByRoleSn(String... sns) {
        return employeeMapper.selectByRoleSn(sns);
    }
}
