package com.chen.core.goods.service;

import com.chen.core.goods.bo.DishBO;
import com.chen.core.goods.entity.DishDO;

/**
 * 菜品的信息
 */
public interface DishService {

    /**
     * 新增菜品和口味信息
     * @param dishBO 菜品和口味的信息
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean insertDish(DishBO dishBO);


    /**
     * 根据分类id查询菜品数量
     * @param categoryId 分类Id
     * @return 菜品数量
     */
    Integer countByCategoryId(Long categoryId);


    /**
     * 根据菜品名称获取菜品信息
     * @param name 菜品名称
     * @return 菜品信息
     */
    DishDO getByName(String name);
}
