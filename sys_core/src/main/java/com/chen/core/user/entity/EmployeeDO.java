package com.chen.core.user.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/** 商家内部的员工信息 */
@Data
public class EmployeeDO implements Serializable {

    /** 主键,自增 */
    private Long id;

    /** 员工姓名 */
    private String name;

    /** 员工用户名 */
    private String username;

    /** 员工密码 */
    private String password;

    /** 员工手机号 */
    private String phone;

    /** 员工性别 */
    private String sex;

    /** 员工身份证号 */
    private String idNumber;

    /** 员工账号状态 */
    private Integer status;

    /** 员工账号创建时间 */
    private Timestamp createTime;

    /** 员工账号最后修改时间  */
    private Timestamp updateTime;

    /** 员工账号创建人id */
    private Long createUser;

    /** 员工账号最后修改人id */
    private Long updateUser;

}
