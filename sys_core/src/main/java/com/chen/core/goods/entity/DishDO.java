package com.chen.core.goods.entity;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/** 菜品的信息 */
@Data
public class DishDO implements Serializable {

    /** 主键自增 */
    private Long id;

    /** 菜品名称 */
    private String name;

    /** 菜品分类id */
    private Long categoryId;

    /** 菜品价格 */
    private BigDecimal price;

    /** 图片路径 */
    private String image;

    /** 菜品描述 */
    private String description;

    /** 售卖状态, 1：起售， 0：停售 */
    private Integer status;

    /** 菜品创建时间 */
    private Timestamp createTime;

    /** 菜品最后修改时间 */
    private Timestamp updateTime;

    /** 菜品创建人id */
    private Long createUser;

    /** 菜品最后修改人id */
    private Long updateUser;

}
