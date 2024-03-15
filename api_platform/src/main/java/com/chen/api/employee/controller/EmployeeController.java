package com.chen.api.employee.controller;

import cn.hutool.core.bean.BeanUtil;
import com.chen.api.employee.po.EmployeeCreatePO;
import com.chen.api.employee.po.EmployeeLoginPO;
import com.chen.api.employee.vo.EmployeeLoginVO;
import com.chen.common.constant.JwtClaimsConstant;
import com.chen.common.constant.PasswordConstant;
import com.chen.common.constant.StatusConstant;
import com.chen.common.context.BaseContext;
import com.chen.common.properties.JwtProperties;
import com.chen.common.result.Result;
import com.chen.common.utils.DateUtil;
import com.chen.common.utils.JwtUtil;
import com.chen.common.utils.LogUtil;
import com.chen.core.user.entity.EmployeeDO;
import com.chen.core.user.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@Api(tags = "1.1 员工接口")
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
     * @param po 员工入参
     * @return 员工信息
     */
    @ApiOperation(value = "1.1.1 员工登录")
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginPO po) {
        LogUtil.printInfo("员工登录：{"+po+"}");

        EmployeeDO employeeDO = new EmployeeDO();
        BeanUtil.copyProperties(po, employeeDO);
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
     * @return 退出结果
     */
    @ApiOperation(value = "1.1.2 员工退出")
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * 添加员工
     * @param po 创建员工PO
     * @return
     */
    @ApiOperation(value = "1.1.3 添加员工")
    @PostMapping
    public Result create(@RequestBody EmployeeCreatePO po){

        EmployeeDO employeeDO = new EmployeeDO();
        BeanUtil.copyProperties(po, employeeDO);
        employeeDO.setCreateTime(DateUtil.getCurTime());
        employeeDO.setUpdateTime(DateUtil.getCurTime());
        employeeDO.setStatus(StatusConstant.ENABLE);

        //设置员工默认密码为123456
        employeeDO.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes(StandardCharsets.UTF_8)));

        employeeDO.setCreateUser(BaseContext.getCurrentId());
        employeeDO.setUpdateUser(BaseContext.getCurrentId());
        boolean flag = employeeService.insertEmployee(employeeDO);
        return Result.success();
    }

}
