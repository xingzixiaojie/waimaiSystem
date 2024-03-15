package com.chen.core.user.mapper;

import com.chen.core.user.entity.EmployeeDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 内部的员工信息操作
 */
@Mapper
public interface EmployeeMapper {

    /**
     * 新增员工信息
     * @param employeeDO 员工信息
     * @return 影响条数
     */
    int insertEmployee(EmployeeDO employeeDO);

    /**
     * 修改员工信息
     * @param employeeDO 员工信息
     * @return 影响条数
     */
    int updateEmployee(EmployeeDO employeeDO);


    /**
     * 修改员工账号状态
     * @param id 员工id
     * @param status 员工账号状态，1：启用， 0：禁用
     * @return 影响条数
     */
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);

    /**
     * 根据员工Id查询员工信息
     * @param id 员工Id
     * @return 员工信息
     */
    EmployeeDO getById(@Param("id") Long id);

    /**
     * 根据员工用户名查询员工信息
     * @param username 员工用户名
     * @return 员工信息
     */
    EmployeeDO getByUsername(@Param("username") String username);

    /**
     * 查询员工信息列表
     * @param employeeName 员工姓名，支持模糊查询，查询全部填NULL
     * @return 员工信息列表
     */
    List<EmployeeDO> listAll(@Param("employeeName") String employeeName);

}
