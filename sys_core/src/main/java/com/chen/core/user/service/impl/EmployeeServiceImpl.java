package com.chen.core.user.service.impl;

import com.chen.common.constant.MessageConstant;
import com.chen.common.constant.StatusConstant;
import com.chen.common.exception.AccountLockedException;
import com.chen.common.exception.AccountNotFoundException;
import com.chen.common.exception.PasswordErrorException;
import com.chen.core.user.entity.EmployeeDO;
import com.chen.core.user.mapper.EmployeeMapper;
import com.chen.core.user.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 员工信息操作
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    /** 内部的员工信息操作 */
    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录操作
     * @param employeeDO 员工登录信息
     * @return 员工信息
     */
    @Override
    public EmployeeDO login(EmployeeDO employeeDO) {
        String username = employeeDO.getUsername();
        String password = employeeDO.getPassword();

        EmployeeDO employee = employeeMapper.getByUsername(username);

        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        // TODO 后期需要进行md5加密，然后再进行比对
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }

}
