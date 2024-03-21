package com.chen.core.goods.mapper;

import com.chen.common.enumeration.OperationType;
import com.chen.core.annotation.AutoFill;
import com.chen.core.goods.entity.DishDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 菜品的信息
 */
@Mapper
public interface DishMapper {

    /**
     * 新增菜品信息
     * @param dishDO 菜品信息
     * @return 影响条数
     */
    @AutoFill(value = OperationType.INSERT)
    int insert(DishDO dishDO);

    /**
     * 根据分类id查询菜品数量
     * @param categoryId 分类Id
     * @return 菜品数量
     */
    int countByCategoryId(@Param("categoryId") Long categoryId);

    /**
     * 根据菜品名称获取菜品信息
     * @param name 菜品名称
     * @return 菜品信息
     */
    DishDO getByName(@Param("name") String name);

}
