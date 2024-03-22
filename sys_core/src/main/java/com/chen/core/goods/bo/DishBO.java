package com.chen.core.goods.bo;

import com.chen.core.goods.entity.DishFlavorDO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** 菜品信息BO */
@ApiModel("菜品信息BO")
@Data
public class DishBO implements Serializable {

    /** 主键自增 */
    private Long id;

    /** 菜品名称 */
    private String name;

    /** 菜品分类id */
    private Long categoryId;

    /** 菜品价格 */
    private BigDecimal price;

    /** 图片路径 */
    private String image;

    /** 菜品描述 */
    private String description;

    /** 售卖状态, 1：起售， 0：停售 */
    private Integer status;

    /** 口味集合 */
    private List<DishFlavorDO> flavorList = new ArrayList<>();

}
