package com.lz.crm.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeQuery extends QueryObject {
    private Long deptId = -1L; //部门id

}
