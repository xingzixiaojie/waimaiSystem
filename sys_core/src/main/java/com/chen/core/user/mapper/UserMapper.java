package com.chen.core.user.mapper;

import com.chen.core.user.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * C端用户的信息
 */
@Mapper
public interface UserMapper {

    /**
     * 新增用户信息
     * @param userDO 用户信息
     * @return 影响条数
     */
    int insert(UserDO userDO);

    /**
     * 根据微信唯一标识获取用户
     * @param openId 微信唯一标识
     * @return 用户信息
     */
    UserDO getByOpenId(@Param("openId") String openId);

}
