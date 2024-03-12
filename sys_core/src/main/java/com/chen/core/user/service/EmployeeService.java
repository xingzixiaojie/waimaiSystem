package com.chen.core.user.service;

import com.chen.core.user.entity.EmployeeDO;

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

}
