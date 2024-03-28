package com.chen.api.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@ApiModel("下单VO")
@Data
public class OrderSubmitVO implements Serializable {

    @ApiModelProperty(required = true, value = "订单Id")
    private Long id;

    @ApiModelProperty(required = true, value = "订单号")
    private String orderNumber;

    @ApiModelProperty(required = true, value = "订单金额")
    private BigDecimal orderAmount;

    @ApiModelProperty(required = true, value = "下单时间")
    private Timestamp orderTime;

}
