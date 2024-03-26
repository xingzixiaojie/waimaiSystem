package com.chen.api.user.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("C端用户登录")
@Data
public class UserLoginPO implements Serializable {

    @ApiModelProperty(required = true, value = "微信授权码")
    private String code;

}
