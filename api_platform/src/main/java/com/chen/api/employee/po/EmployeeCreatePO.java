package com.chen.api.employee.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 创建员工PO
 */
@ApiModel("创建员工PO")
@Data
public class EmployeeCreatePO implements Serializable {

    /** 员工Id */
    @ApiModelProperty(value = "员工Id", required = false)
    private Integer id;

    /** 员工身份证 */
    @ApiModelProperty(value = "员工身份证", required = true)
    private String idNumber;

    /** 员工姓名 */
    @ApiModelProperty(value = "员工姓名", required = true)
    private String name;

    /** 员工手机号 */
    @ApiModelProperty(value = "员工手机号", required = true)
    private String phone;

    /** 性别 */
    @ApiModelProperty(value = "性别", required = true)
    private String sex;

    /** 员工用户名 */
    @ApiModelProperty(value = "员工用户名", required = true)
    private String username;

}
