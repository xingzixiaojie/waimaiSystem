package com.chen.core.goods.mapper;

import com.chen.core.goods.entity.DishFlavorDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜品的口味信息
 */
@Mapper
public interface DishFlavorMapper {

    /**
     * 新增菜品的口味信息
     * @param dishFlavorDO 口味信息
     * @return 影响条数
     */
    int insert(DishFlavorDO dishFlavorDO);

    /**
     * 批量新增菜品的口味信息
     * @param dishFlavorDOList
     * @return 影响条数
     */
    int insertList(List<DishFlavorDO> dishFlavorDOList);

    /**
     * 获取所有菜品口味信息
     * @return 菜品口味信息集合
     */
    List<DishFlavorDO> listAll();

}
