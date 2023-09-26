package com.sky.service;

import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employee
     */
    void save(Employee employee);

    /**
     * 员工分页查询
     * @param pageQueryDTO
     * @return
     */
    PageResult page(EmployeePageQueryDTO pageQueryDTO);

    /**
     * 更新员工状态
     * @param status
     * @param id
     */
    void updateStatus(Integer status, Long id);
}
