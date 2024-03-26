package com.chen.core.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chen.common.constant.MessageConstant;
import com.chen.common.exception.LoginFailedException;
import com.chen.common.properties.WeChatProperties;
import com.chen.common.utils.DateUtil;
import com.chen.common.utils.HttpClientUtil;
import com.chen.core.user.bo.UserLoginBO;
import com.chen.core.user.entity.UserDO;
import com.chen.core.user.mapper.UserMapper;
import com.chen.core.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/** 用户信息操作 */
@Service
public class UserServiceImpl implements UserService {

    /** C端用户的信息 */
    @Resource
    private UserMapper userMapper;

    /** 微信接口属性 */
    @Resource
    private WeChatProperties weChatProperties;

    /** 微信服务接口地址 */
    public final static String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    /**
     * 微信登录
     * @param userLoginBO 微信授权码
     * @return 用户信息
     */
    @Override
    public UserDO wxLogin(UserLoginBO userLoginBO) {
        //调用微信接口服务获取openid
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appid", weChatProperties.getAppid());
        paramMap.put("secret", weChatProperties.getSecret());
        paramMap.put("js_code", userLoginBO.getCode());
        paramMap.put("grant_type", "authorization_code");
        String json = HttpClientUtil.doGet(WX_LOGIN, paramMap);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = jsonObject.getString("openid");
        //判断openid是否有效
        if(openid == null || openid == ""){
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }
        //判断用户是否为新用户
        UserDO userDO = userMapper.getByOpenId(openid);
        UserDO userFreshDO = new UserDO();
        if(userDO == null){
            userFreshDO.setCreateTime(DateUtil.getCurTime());
            userFreshDO.setOpenid(openid);
            userMapper.insert(userFreshDO);
            return userFreshDO;
        }
        return userDO;
    }

}
