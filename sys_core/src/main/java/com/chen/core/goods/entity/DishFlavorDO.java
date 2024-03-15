package com.chen.core.goods.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private String name;

    /** 口味数据list */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private String value;

}
