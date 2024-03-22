package com.chen.core.goods.mapper;

import com.chen.common.enumeration.OperationType;
import com.chen.core.annotation.AutoFill;
import com.chen.core.goods.entity.DishDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * 根据菜品id集合批量删除菜品信息
     * @param ids 菜品id集合
     * @return 影响条数
     */
    int deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 修改菜品信息
     * @param dishDO 菜品信息
     * @return 影响条数
     */
    @AutoFill(value = OperationType.UPDATE)
    int update(DishDO dishDO);

    /**
     * 根据分类id查询菜品数量
     * @param categoryId 分类Id
     * @return 菜品数量
     */
    int countByCategoryId(@Param("categoryId") Long categoryId);

    /**
     * 根据菜品Id查询菜品信息
     * @param id 菜品Id
     * @return 菜品信息
     */
    DishDO getById(@Param("id") Long id);

    /**
     * 根据菜品名称获取菜品信息
     * @param name 菜品名称
     * @return 菜品信息
     */
    DishDO getByName(@Param("name") String name);

    /**
     * 查询全部菜品信息
     * @param name 菜品名称，支持模糊查询，查询全部填NULL
     * @param categoryId 菜品分类id，查询全部填NULL
     * @param status 售卖状态，1：起售， 0：停售,查询全部填NULL
     * @return 菜品信息集合
     */
    List<DishDO> list(@Param("name") String name, @Param("categoryId") Long categoryId, @Param("status") Integer status);

}
