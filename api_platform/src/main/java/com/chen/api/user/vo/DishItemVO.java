package com.chen.api.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("套餐菜品VO")
@Data
public class DishItemVO implements Serializable {

    @ApiModelProperty(required = true, value = "菜品名字")
    private String name;

    @ApiModelProperty(required = true, value = "份数")
    private Integer copies;

    @ApiModelProperty(required = true, value = "菜品图片")
    private String image;

    @ApiModelProperty(required = true, value = "菜品描述")
    private String description;

}
