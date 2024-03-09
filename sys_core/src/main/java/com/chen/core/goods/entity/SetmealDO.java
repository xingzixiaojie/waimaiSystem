package com.chen.core.goods.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/** 套餐的信息 */
@Data
public class SetmealDO implements Serializable {

    /** 主键，自增 */
    private Long id;

    /** 套餐名称 */
    private String name;

    /** 菜品分类id */
    private Long categoryId;

    /** 套餐价格 */
    private BigDecimal price;

    /** 图片路径 */
    private String image;

    /** 套餐描述 */
    private String description;

    /** 售卖状态, 1：起售， 0：停售*/
    private Integer status;

    /** 菜品创建时间 */
    private Timestamp createTime;

    /** 菜品最后修改时间  */
    private Timestamp updateTime;

    /** 菜品创建人id */
    private Long createUser;

    /** 菜品最后修改人id */
    private Long updateUser;

}
