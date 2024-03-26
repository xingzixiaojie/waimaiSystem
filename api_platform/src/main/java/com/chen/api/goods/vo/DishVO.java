package com.chen.api.goods.vo;

import com.chen.core.goods.entity.DishFlavorDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ApiModel("菜品的信息VO")
@Data
public class DishVO implements Serializable {

    /** 主键自增 */
    @ApiModelProperty(value = "主键自增")
    private Long id;

    /** 菜品名称 */
    @ApiModelProperty(required = true, value = "菜品名称")
    private String name;

    /** 菜品分类id */
    @ApiModelProperty(value = "菜品分类id")
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

    /** 菜品最后修改时间, 格式：yyyy-MM-dd HH:mm:ss */
    @ApiModelProperty(required = true, value = "菜品最后修改时间, 格式：yyyy-MM-dd HH:mm:ss")
    private String updateTime;

    /** 分类名称 */
    @ApiModelProperty(required = true, value = "分类名称")
    private String categoryName;

    /** 菜品关联的口味 */
    @ApiModelProperty(required = true, value = "菜品关联的口味")
    private List<DishFlavorDO> flavors = new ArrayList<>();

}
