package com.chen.core.goods.service;

import com.chen.core.goods.entity.SetmealDishDO;

import java.util.List;

/** 套餐和菜品的关联关系 */
public interface SetmealDishService {

    /**
     * 批量插入套餐菜品关系信息
     * @param setmealDishDOList 套餐菜品关系信息集合
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean insertBatch(List<SetmealDishDO> setmealDishDOList);

    /**
     * 根据套餐id集合批量删除套餐菜品关系信息
     * @param setmealIds 套餐id集合
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean deleteBatchBySetmealIds(List<Long> setmealIds);

    /**
     * 根据套餐id查询套餐菜品关联信息
     * @param setmealId 套餐id
     * @return 套餐和菜品关联信息
     */
    List<SetmealDishDO> listBySetmealId(Long setmealId);


    /**
     * 获取所有的套餐和菜品关联信息
     * @return 套餐和菜品关联信息
     */
    List<SetmealDishDO> listAll();


}
