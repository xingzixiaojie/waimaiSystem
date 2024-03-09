package com.chen.core.user.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * C端用户的信息
 */
@Data
public class UserDO implements Serializable {

    /** 主键,自增 */
    private Long id;

    /** 微信用户唯一标识 */
    private String openid;

    /** 用户姓名  */
    private String name;

    /** 用户手机号 */
    private String phone;

    /** 用户性别 */
    private String sex;

    /** 用户身份证号 */
    private String idNumber;

    /** 微信用户头像路径 */
    private String avatar;

    /** 注册时间 */
    private Timestamp createTime;

}
