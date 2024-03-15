package com.chen.core.user.service.impl;

import com.chen.common.constant.MessageConstant;
import com.chen.common.constant.StatusConstant;
import com.chen.common.exception.AccountLockedException;
import com.chen.common.exception.AccountNotFoundException;
import com.chen.common.exception.PasswordErrorException;
import com.chen.common.result.QueryPage;
import com.chen.core.user.entity.EmployeeDO;
import com.chen.core.user.mapper.EmployeeMapper;
import com.chen.core.user.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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
        //MD5加密
        String md5Password = DigestUtils.md5DigestAsHex(employeeDO.getPassword().getBytes());
        if (!md5Password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        return employee;
    }

    /**
     * 新增员工信息
     * @param employeeDO 员工信息
     * @return 操作成功：返回true，操作失败：返回false
     */
    @Override
    public boolean insertEmployee(EmployeeDO employeeDO) {
        return employeeMapper.insertEmployee(employeeDO) > 0;
    }

    /**
     * 根据员工用户名查询员工信息
     * @param username 员工用户名
     * @return 员工信息
     */
    @Override
    public EmployeeDO getByUsername(String username) {
        EmployeeDO byUsername = employeeMapper.getByUsername(username);
        return byUsername;
    }

    /**
     * 查询员工信息列表
     * @param page 分页对象
     * @param employeeName 员工姓名，支持模糊查询，查询全部填NULL
     * @return 员工信息列表
     */
    @Override
    public PageInfo<EmployeeDO> listAll(QueryPage page, String employeeName) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return new PageInfo<>(employeeMapper.listAll(employeeName));
    }

}
