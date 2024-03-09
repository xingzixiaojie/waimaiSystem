package com.chen.core.goods.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/** 套餐和菜品的关联关系 */
@Data
public class SetmealDishDO implements Serializable {

    /** 主键，自增 */
    private Long id;

    /** 套餐id */
    private Long setmealId;

    /** 菜品id */
    private Long dishId;

    /** 菜品名称 */
    private String name;

    /** 菜品单价 */
    private BigDecimal price;

    /** 菜品份数 */
    private Integer copies;

}
