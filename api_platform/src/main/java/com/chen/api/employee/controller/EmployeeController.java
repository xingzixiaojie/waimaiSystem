package com.chen.api.employee.controller;

import cn.hutool.core.bean.BeanUtil;
import com.chen.api.employee.po.EmployeeLoginPO;
import com.chen.api.employee.vo.EmployeeLoginVO;
import com.chen.common.constant.JwtClaimsConstant;
import com.chen.common.properties.JwtProperties;
import com.chen.common.result.Result;
import com.chen.common.utils.JwtUtil;
import com.chen.common.utils.LogUtil;
import com.chen.core.user.entity.EmployeeDO;
import com.chen.core.user.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
public class EmployeeController {

    /** 员工信息操作 */
    @Resource
    private EmployeeService employeeService;

    /** Jwt相关属性 */
    @Resource
    private JwtProperties jwtProperties;

    /**
     * 员工登录
     * @param employeeLoginPO 员工入参
     * @return 员工信息
     */
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginPO employeeLoginPO) {
        LogUtil.printInfo("员工登录：{"+employeeLoginPO+"}");

        EmployeeDO employeeDO = new EmployeeDO();
        BeanUtil.copyProperties(employeeLoginPO, employeeDO);
        EmployeeDO employee = employeeService.login(employeeDO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = new EmployeeLoginVO();
        employeeLoginVO.setId(employee.getId());
        employeeLoginVO.setName(employee.getName());
        employeeLoginVO.setToken(token);
        employeeLoginVO.setUserName(employee.getName());

        return Result.success(employeeLoginVO);
    }

    /**
     * 员工退出
     *
     * @return 退出结果
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

}
