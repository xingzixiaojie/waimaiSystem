package com.chen.core.user.mapper;

import com.chen.core.user.entity.EmployeeDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 内部的员工信息操作
 */
@Mapper
public interface EmployeeMapper {

    /**
     * 根据员工用户名查询员工信息
     * @param username 员工用户名
     * @return 员工信息
     */
    EmployeeDO getByUsername(@Param("username") String username);

    /**
     * 新增员工信息
     * @param employeeDO 员工信息
     * @return 影响条数
     */
    int insertEmployee(EmployeeDO employeeDO);

}
