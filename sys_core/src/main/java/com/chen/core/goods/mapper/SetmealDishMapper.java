package com.chen.core.goods.mapper;

import com.chen.core.goods.entity.SetmealDishDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 套餐和菜品的关联关系
 */
@Mapper
public interface SetmealDishMapper {

    /**
     * 批量插入套餐菜品关系信息
     * @param setmealDishDOList 套餐菜品关系信息集合
     * @return 影响条数
     */
    int insertBatch(@Param("list") List<SetmealDishDO> setmealDishDOList);

    /**
     * 根据套餐id集合批量删除套餐菜品关系信息
     * @param setmealIds 套餐id集合
     * @return 影响条数
     */
    int deleteBatchBySetmealIds(@Param("list") List<Long> setmealIds);

    /**
     * 根据套餐id查询套餐菜品关联信息
     * @param setmealId 套餐id
     * @return 套餐和菜品关联信息
     */
    List<SetmealDishDO> listBySetmealId(@Param("setmealId") Long setmealId);

    /**
     * 获取所有的套餐和菜品关联信息
     * @return 套餐和菜品关联信息
     */
   List<SetmealDishDO> listAll();

}
