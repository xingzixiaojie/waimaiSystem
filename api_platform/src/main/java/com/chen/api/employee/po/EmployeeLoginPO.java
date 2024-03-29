package com.chen.api.employee.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/** 员工登录时传递的数据模型 */
@ApiModel("员工登录时传递的数据模型")
@Data
public class EmployeeLoginPO implements Serializable {

    /** 用户名 */
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    /** 密码 */
    @ApiModelProperty(value = "密码", required = true)
    private String password;

}
