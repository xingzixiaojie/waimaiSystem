package com.chen.api.user.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("用户购物车PO")
@Data
public class ShoppingCartPO implements Serializable {

    @ApiModelProperty(required = true, value = "菜品id")
    private Long dishId;

    @ApiModelProperty(required = true, value = "套餐id")
    private Long setmealId;

    @ApiModelProperty(required = true, value = "菜品口味")
    private String dishFlavor;

}
