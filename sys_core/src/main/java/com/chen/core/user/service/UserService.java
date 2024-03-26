package com.chen.core.user.service;

import com.chen.core.user.bo.UserLoginBO;
import com.chen.core.user.entity.UserDO;

/** 用户信息操作 */
public interface UserService {


    /**
     * 微信登录
     * @param userLoginBO 微信授权码
     * @return 用户信息
     */
    UserDO wxLogin(UserLoginBO userLoginBO);

}
