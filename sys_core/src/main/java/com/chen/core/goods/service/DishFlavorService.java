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

    /**
     * 根据菜品口味id集合批量删除菜品口味信息
     * @param ids 菜品口味id集合
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean deleteByIds(List<Long> ids);

    /**
     * 根据菜品id删除菜品口味
     * @param dishId 菜品id
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean deteleByDishId(Long dishId);


    /**
     * 根据菜品id查询菜品口味信息
     * @param dishId 品id
     * @return 菜品口味信息集合
     */
    List<DishFlavorDO> listByDishId(Long dishId);

    /**
     * 获取所有菜品口味信息
     * @return 菜品口味信息集合
     */
    List<DishFlavorDO> listAll();

}
