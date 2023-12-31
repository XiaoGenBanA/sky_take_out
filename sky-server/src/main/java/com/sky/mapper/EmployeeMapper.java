package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.autofill;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 新增员工
     * @param employee
     */
    @autofill(value = OperationType.INSERT)
    @Insert("INSERT INTO `sky_take_out`.`employee` (`name`, `username`, `password`, `phone`, `sex`, `id_number`, `status`, `create_time`, `update_time`, `create_user`, `update_user`) " +
            "VALUES (#{name}, #{username}, #{password}, #{phone}, #{sex}, #{idNumber}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void save(Employee employee);

    /**
     * 员工分页查询
     * @param name
     * @return
     */
    Page<Employee> pageQuery(String name);

    /**
     * 员工更新
     * @param employee
     */

    void update(Employee employee);

    /**
     * 员工查询
     * @param id
     * @return
     */
    @Select("select * from employee where id = #{id}")
    Employee getById(Integer id);
}
