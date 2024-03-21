package com.chen.core.goods.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 套餐的信息
 */
@Mapper
public interface SetmealMapper {


    /**
     * 根据分类id查询套餐的数量
     * @param categoryId 分类Id
     * @return 套餐的数量
     */
    int countByCategoryId(@Param("categoryId") Long categoryId);

}
