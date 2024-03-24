package com.chen.core.goods.bo;

import com.chen.core.goods.entity.SetmealDishDO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


@Data
public class SetmealBO implements Serializable {

    /** 主键，自增 */
    private Long id;

    /** 套餐名称 */
    private String name;

    /** 菜品分类id */
    private Long categoryId;

    /** 套餐价格 */
    private BigDecimal price;

    /** 图片路径 */
    private String image;

    /** 套餐描述 */
    private String description;

    /** 售卖状态, 1：起售， 0：停售*/
    private Integer status;

    /**套餐菜品关系集合 */
    private List<SetmealDishDO> setmealDishes;

}
