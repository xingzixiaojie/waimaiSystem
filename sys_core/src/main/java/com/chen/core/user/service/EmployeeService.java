package com.chen.core.user.service;

import com.chen.common.result.QueryPage;
import com.chen.core.user.entity.EmployeeDO;
import com.github.pagehelper.PageInfo;

/**
 * 员工信息操作
 */
public interface EmployeeService {

    /**
     * 员工登录操作
     * @param employeeDO 员工登录信息
     * @return 员工信息
     */
    EmployeeDO login(EmployeeDO employeeDO);

    /**
     * 新增员工信息
     * @param employeeDO 员工信息
     * @return 操作成功：返回true，操作失败：返回false
     */
    boolean insertEmployee(EmployeeDO employeeDO);

    /**
     * 根据员工用户名查询员工信息
     * @param username 员工用户名
     * @return 员工信息
     */
    EmployeeDO getByUsername(String username);

    /**
     * 查询员工信息列表
     * @param page 分页对象
     * @param employeeName 员工姓名，支持模糊查询，查询全部填NULL
     * @return 员工信息列表
     */
    PageInfo<EmployeeDO> listAll (QueryPage page, String employeeName);
}
