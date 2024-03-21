package com.chen.core.goods.service;

import com.chen.core.goods.entity.DishFlavorDO;

import java.util.List;

/**
 * 菜品的口味信息
 */
public interface DishFlavorService {

    /**
     * 新增菜品口味信息
     * @param dishFlavorDO 菜品的口味信息
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean insertDishFlavor(DishFlavorDO dishFlavorDO);

    /**
     * 批量新增菜品口味信息
     * @param dishFlavorDOList 菜品的口味信息集合
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean insertDishFlavor(List<DishFlavorDO> dishFlavorDOList);


}
