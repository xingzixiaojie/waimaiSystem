package com.chen.core.goods.service;

/**
 * 菜品的信息
 */
public interface DishService {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId 分类Id
     * @return 菜品数量
     */
    Integer countByCategoryId(Long categoryId);

}
