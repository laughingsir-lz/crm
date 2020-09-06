package com.lz.crm.service;

import com.lz.crm.domain.Employee;
import com.lz.crm.query.QueryObject;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IEmployeeService {
    void delete(Long id);

    void save(Employee employee, Long[] ids);

    Employee get(Long id);

    List<Employee> listAll();

    void update(Employee employee, Long[] ids);

    Employee login(String username, String password);

    PageInfo query(QueryObject qo);

    void batchDelete(Long[] ids);

    Employee selectByName(String name);

    Workbook exportXls();

    void importXls(MultipartFile file) throws IOException, Exception;

    List<Employee> selectByRoleSn(String... sns);
}
