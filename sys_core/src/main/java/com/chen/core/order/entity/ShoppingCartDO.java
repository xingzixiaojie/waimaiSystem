package com.chen.core.order.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/** C端用户的购物车信息 */
@Data
public class ShoppingCartDO implements Serializable {

    /** 主键，自增 */
    private Long id;

    /** 商品名称 */
    private String name;

    /** 商品图片路径 */
    private String image;

    /** 用户id */
    private Long userId;

    /** 菜品id */
    private Long dishId;

    /** 套餐id */
    private Long setmealId;

    /** 菜品口味 */
    private String dishFlavor;

    /** 商品数量 */
    private Integer number;

    /** 商品单价 */
    private BigDecimal amount;

    /** 创建时间 */
    private Timestamp createTime;

}
