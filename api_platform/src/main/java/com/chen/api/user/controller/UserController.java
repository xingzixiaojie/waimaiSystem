package com.chen.api.user.controller;

import cn.hutool.core.bean.BeanUtil;
import com.chen.api.user.po.UserLoginPO;
import com.chen.api.user.vo.UserLoginVO;
import com.chen.common.constant.JwtClaimsConstant;
import com.chen.common.properties.JwtProperties;
import com.chen.common.result.Result;
import com.chen.common.utils.JwtUtil;
import com.chen.core.user.bo.UserLoginBO;
import com.chen.core.user.entity.UserDO;
import com.chen.core.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "10.1 C端用户相关接口")
@RestController
@RequestMapping("/user/user")
public class UserController {

    /** 用户信息操作 */
    @Resource
    private UserService userService;

    /** Jwt相关属性 */
    @Resource
    private JwtProperties jwtProperties;

    @ApiOperation("10.1.1 用户微信登录")
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginPO po){
        UserLoginBO userLoginBO = new UserLoginBO();
        BeanUtil.copyProperties(po, userLoginBO);
        UserDO userLoginDO = userService.wxLogin(userLoginBO);

        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, userLoginDO.getId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
        UserLoginVO userLoginVO = new UserLoginVO();
        BeanUtil.copyProperties(userLoginDO, userLoginVO);
        userLoginVO.setToken(token);
        return Result.success(userLoginVO);
    }

}
