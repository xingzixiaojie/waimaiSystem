package com.chen.api.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("C端用户登录VO")
@Data
public class UserLoginVO implements Serializable {

    @ApiModelProperty(required = true, value = "用户ID")
    private Long id;

    @ApiModelProperty(required = true, value = "用户微信唯一标识")
    private String openid;

    @ApiModelProperty(required = true, value = "用户Token")
    private String token;

}
