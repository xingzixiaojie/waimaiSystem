package com.chen.api.goods.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("创建分类PO")
@Data
public class CategoryCreatePO implements Serializable {

    //主键
    @ApiModelProperty("主键")
    private Long id;

    //类型 1 菜品分类 2 套餐分类
    @ApiModelProperty(value = "类型 1 菜品分类 2 套餐分类", required = true)
    private Integer type;

    //分类名称
    @ApiModelProperty(value = "分类名称", required = true)
    private String name;

    //排序
    @ApiModelProperty(value = "排序")
    private Integer sort;

}
