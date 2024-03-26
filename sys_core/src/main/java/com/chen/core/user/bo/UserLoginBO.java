package com.chen.core.user.bo;

import lombok.Data;

import java.io.Serializable;

/** C端用户登录 */
@Data
public class UserLoginBO implements Serializable {

    /** 微信授权码 */
    private String code;

}
