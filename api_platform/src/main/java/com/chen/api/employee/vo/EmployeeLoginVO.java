package com.chen.api.employee.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("员工登录返回的数据格式")
@Data
public class EmployeeLoginVO implements Serializable {

    @ApiModelProperty(value = "主键值", required = true)
    private Long id;

    @ApiModelProperty(value = "用户名", required = true)
    private String userName;

    @ApiModelProperty(value = "姓名", required = true)
    private String name;

    @ApiModelProperty(value = "jwt令牌", required = true)
    private String token;

}
