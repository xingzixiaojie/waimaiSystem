package com.chen.core.goods.mapper;

import com.chen.core.goods.entity.DishFlavorDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * 根据菜品口味id集合批量删除菜品口味信息
     * @param ids 菜品口味id集合
     * @return 影响条数
     */
    int deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 根据菜品id删除菜品口味
     * @param dishId 菜品id
     * @return 影响条数
     */
    int deteleByDishId(@Param("dishId") Long dishId);

    /**
     * 根据菜品id查询菜品口味信息
     * @param dishId 品id
     * @return 菜品口味信息集合
     */
    List<DishFlavorDO> listByDishId(@Param("dishId") Long dishId);

    /**
     * 获取所有菜品口味信息
     * @return 菜品口味信息集合
     */
    List<DishFlavorDO> listAll();

}
