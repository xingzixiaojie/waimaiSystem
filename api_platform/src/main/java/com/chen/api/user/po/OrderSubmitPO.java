package com.chen.api.user.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@ApiModel("下单PO")
@Data
public class OrderSubmitPO implements Serializable {

    @ApiModelProperty(required = true, value = "地址簿Id")
    private Long addressBookId;

    @ApiModelProperty(required = true, value = "付款方式")
    private int payMethod;

    @ApiModelProperty(required = true, value = "备注")
    private String remark;

    @ApiModelProperty(required = true, value = "预计送达时间")
    private Timestamp estimateDeliveryTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @ApiModelProperty(required = true, value = "配送状态， 1：立即送出，0：选择具体时间")
    private Integer deliveryStatus;

    @ApiModelProperty(required = true, value = "餐具数量")
    private Integer tablewareNumber;

    @ApiModelProperty(required = true, value = "餐具数量状态， 1：按餐量提供 0：选择具体数量")
    private Integer tablewareStatus;

    @ApiModelProperty(required = true, value = "打包费")
    private Integer packAmount;

    @ApiModelProperty(required = true, value = "总金额")
    private BigDecimal amount;

}
