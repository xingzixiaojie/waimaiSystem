package com.chen.core.goods.mapper;

import com.chen.core.goods.entity.SetmealDishDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 套餐和菜品的关联关系
 */
@Mapper
public interface SetmealDishMapper {


    /**
     * 获取所有的套餐和菜品关联信息
     * @return 套餐和菜品关联信息
     */
   List<SetmealDishDO> listAll();

}
