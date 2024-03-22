package com.chen.api.goods.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("菜品分页查询PO")
@Data
public class DishPageQueryPO implements Serializable {

    @ApiModelProperty(required = true, value = "页码")
    private int page;

    //每页记录数
    @ApiModelProperty(required = true, value = "每页记录数")
    private int pageSize;

    @ApiModelProperty(value = "菜品名称")
    private String name;

    @ApiModelProperty(value = "分类id")
    private Long categoryId;

    @ApiModelProperty(value = "售卖状态， 1：起售， 0：停售")
    private Integer status;

}
