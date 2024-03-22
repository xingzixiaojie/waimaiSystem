package com.chen.core.goods.service;

import com.chen.core.goods.entity.SetmealDishDO;

import java.util.List;

/** 套餐和菜品的关联关系 */
public interface SetmealDishService {

    /**
     * 获取所有的套餐和菜品关联信息
     * @return 套餐和菜品关联信息
     */
    List<SetmealDishDO> listAll();


}
