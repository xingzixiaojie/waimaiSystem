package com.chen.api.goods.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("分页查询分类PO")
@Data
public class CategoryPageQueryPO implements Serializable {

    @ApiModelProperty(value = "页码", required = true)
    private int page;

    //每页记录数
    @ApiModelProperty(value = "每页记录数", required = true)
    private int pageSize;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "分类类型 1菜品分类  2套餐分类")
    private Integer type;

}
