package com.chen.api.goods.po;

import com.chen.core.goods.entity.SetmealDishDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ApiModel("套餐信息PO")
@Data
public class SetmealPO implements Serializable {

    /** 主键，自增 */
    @ApiModelProperty(value = "套餐id")
    private Long id;

    /** 套餐名称 */
    @ApiModelProperty(required = true, value = "套餐名称")
    private String name;

    /** 菜品分类id */
    @ApiModelProperty(required = true, value = "菜品分类id")
    private Long categoryId;

    /** 套餐价格 */
    @ApiModelProperty(required = true, value = "套餐价格")
    private BigDecimal price;

    /** 图片路径 */
    @ApiModelProperty(required = true, value = "图片路径")
    private String image;

    /** 套餐描述 */
    @ApiModelProperty(value = "套餐描述")
    private String description;

    /** 售卖状态, 1：起售， 0：停售*/
    @ApiModelProperty(required = true, value = "售卖状态, 1：起售， 0：停售")
    private Integer status;

    /**套餐菜品关系集合 */
    @ApiModelProperty(required = true, value = "套餐菜品关系集合")
    private List<SetmealDishDO> setmealDishes;
}
