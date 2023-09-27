package com.sky.service;

import com.sky.annotation.autofill;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
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

    /**
     * 员工查询
     * @param id
     * @return
     */
    Employee getById(Integer id);

    /**
     * 员工修改
     * @param employee
     */
    @autofill(value = OperationType.UPDATE)
    void updateEmployee(Employee employee);
}
