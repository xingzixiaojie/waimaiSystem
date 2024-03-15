package com.chen.api.employee.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.chen.api.employee.po.EmployeeCreatePO;
import com.chen.api.employee.po.EmployeeLoginPO;
import com.chen.api.employee.po.EmployeeQueryPO;
import com.chen.api.employee.po.EmployeeUpdatePO;
import com.chen.api.employee.vo.EmployeeLoginVO;
import com.chen.api.employee.vo.EmployeePageResult;
import com.chen.common.constant.JwtClaimsConstant;
import com.chen.common.constant.PasswordConstant;
import com.chen.common.constant.StatusConstant;
import com.chen.common.context.BaseContext;
import com.chen.common.properties.JwtProperties;
import com.chen.common.result.QueryPage;
import com.chen.common.result.Result;
import com.chen.common.utils.DateUtil;
import com.chen.common.utils.JwtUtil;
import com.chen.core.user.entity.EmployeeDO;
import com.chen.core.user.service.EmployeeService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

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


    @ApiOperation(value = "1.1.1 查询所有员工")
    @GetMapping("/page")
    public Result<EmployeePageResult> listAll(EmployeeQueryPO po){
        EmployeePageResult pageResult = new EmployeePageResult();

        PageInfo<EmployeeDO> employeeDOPageInfo = employeeService.listAll(new QueryPage(po.getPage(), po.getPageSize()), po.getName());
        if(employeeDOPageInfo == null || CollUtil.isEmpty(employeeDOPageInfo.getList())){
            pageResult.setTotal(employeeDOPageInfo.getTotal());
            pageResult.setRecords(employeeDOPageInfo.getList());
            return Result.success(pageResult);
        }

        pageResult.setTotal(employeeDOPageInfo.getTotal());
        pageResult.setRecords(employeeDOPageInfo.getList());
        return Result.success(pageResult);
    }



    @ApiOperation(value = "1.1.2 员工登录")
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginPO po) {

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

    @ApiOperation(value = "1.1.4 启用禁用员工账号")
    @PostMapping("/status/{status}")
    public Result updateEmployeeStatus(@PathVariable Integer status, Long id){
        EmployeeDO employeeDO = employeeService.getById(id);
        if (employeeDO == null){
            return Result.error("员工账号不存在");
        }

        boolean flag = employeeService.updateStatus(id, status);
        if (!flag){
            return Result.error("操作失败！");
        }

        return Result.success();
    }

    @ApiOperation(value = "1.1.5 根据id查询员工信息")
    @GetMapping("/{id}")
    public Result<EmployeeDO> getById(@PathVariable Long id){
        EmployeeDO employeeDO = employeeService.getById(id);
        if(employeeDO == null){
            return Result.error("无此员工信息");
        }
        employeeDO.setPassword("****");
        return Result.success(employeeDO);
    }

    @ApiOperation(value = "1.1.6 修改员工信息")
    @PutMapping
    public Result updateEmployee(@RequestBody EmployeeUpdatePO po){
        EmployeeDO employeeDO = new EmployeeDO();
        BeanUtil.copyProperties(po, employeeDO);
        employeeDO.setUpdateTime(DateUtil.getCurTime());
        employeeDO.setUpdateUser(BaseContext.getCurrentId());

        boolean flag = employeeService.updateEmployee(employeeDO);
        if(flag){
            return Result.success();
        }else {
            return Result.error("修改失败!");
        }
    }

    @ApiOperation(value = "1.1.10 员工退出")
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

}
