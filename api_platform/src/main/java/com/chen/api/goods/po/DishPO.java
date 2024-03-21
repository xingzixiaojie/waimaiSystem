package com.chen.api.goods.po;

import com.chen.core.goods.entity.DishFlavorDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ApiModel("菜品信息PO")
@Data
public class DishPO implements Serializable {

    /** 主键自增 */
    @ApiModelProperty(required = true, value = "主键自增")
    private Long id;

    /** 菜品名称 */
    @ApiModelProperty(required = true, value = "菜品名称")
    private String name;

    /** 菜品分类id */
    @ApiModelProperty(required = true, value = "菜品分类id")
    private Long categoryId;

    /** 菜品价格 */
    @ApiModelProperty(required = true, value = "菜品价格")
    private BigDecimal price;

    /** 图片路径 */
    @ApiModelProperty(required = true, value = "图片路径")
    private String image;

    /** 菜品描述 */
    @ApiModelProperty(required = true, value = "菜品描述")
    private String description;

    /** 售卖状态, 1：起售， 0：停售 */
    @ApiModelProperty(required = true, value = "售卖状态, 1：起售， 0：停售")
    private Integer status;

    /** 口味集合 */
    @ApiModelProperty(required = true, value = "口味集合")
    private List<DishFlavorDO> flavors = new ArrayList<>();

}
