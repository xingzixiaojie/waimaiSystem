package com.chen.core.goods.entity;

import lombok.Data;

import java.io.Serializable;

/** 菜品的口味信息 */
@Data
public class DishFlavorDO implements Serializable {

    /** 主键，自增 */
    private Long id;

    /** 菜品id */
    private Long dishId;

    /** 口味名称 */
    private String name;

    /** 口味数据list */
    private String value;

}
