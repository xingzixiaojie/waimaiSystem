package com.chen.core.goods.service;

/**
 * 套餐的信息
 */
public interface SetmealService {

    /**
     * 根据分类id查询套餐的数量
     * @param categoryId 分类Id
     * @return 套餐的数量
     */
    Integer countByCategoryId(Long categoryId);

}
