package com.chen.core.order.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/** C端用户的订单明细数据 */
@Data
public class OrderDetailDO implements Serializable {

    /** 主键，自增 */
    private Long id;

    /** 商品名称 */
    private String name;

    /** 商品图片路径 */
    private String image;

    /** 订单id */
    private Long orderId;

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

}
