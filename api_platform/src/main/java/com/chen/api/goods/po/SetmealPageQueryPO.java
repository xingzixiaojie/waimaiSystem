package com.chen.api.goods.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("套餐分页查询PO")
@Data
public class SetmealPageQueryPO implements Serializable {

    @ApiModelProperty(value = "分类id")
    private Long categoryId;

    @ApiModelProperty(value = "套餐名称")
    private String name;

    @ApiModelProperty(value = "页码", required = true)
    private int page;

    //每页记录数
    @ApiModelProperty(value = "每页记录数", required = true)
    private int pageSize;

    @ApiModelProperty(value = "套餐起售状态, 1：起售， 0：停售")
    private Integer status;
}
