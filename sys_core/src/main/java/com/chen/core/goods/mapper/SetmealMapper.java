package com.chen.core.goods.mapper;

import com.chen.common.enumeration.OperationType;
import com.chen.core.annotation.AutoFill;
import com.chen.core.goods.entity.SetmealDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 套餐的信息
 */
@Mapper
public interface SetmealMapper {

    /**
     * 新增套餐信息
     * @param setmealDO 套餐信息
     * @return 影响条数
     */
    @AutoFill(OperationType.INSERT)
    int insert(SetmealDO setmealDO);

    /**
     * 修改套餐信息
     * @param setmealDO 套餐信息
     * @return 影响条数
     */
    @AutoFill(OperationType.UPDATE)
    int update(SetmealDO setmealDO);

    /**
     * 修改套餐售卖状态
     * @param id 套餐Id
     * @param status 售卖状态， 1：起售， 0：停售
     * @return 影响条数
     */
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);

    /**
     * 根据套餐id集合批量删除套餐信息
     * @param ids 套餐id集合
     * @return 影响条数
     */
    int deleteBatchByIds(@Param("list") List<Long> ids);

    /**
     * 根据分类id查询套餐的数量
     * @param categoryId 分类Id
     * @return 套餐的数量
     */
    int countByCategoryId(@Param("categoryId") Long categoryId);

    /**
     * 根据套餐id查询套餐信息
     * @param id 套餐id
     * @return 套餐信息
     */
    SetmealDO getById(@Param("id") Long id);

    /**
     * 获取所有套餐信息
     * @param categoryId 菜品分类id，查询所有填NULL
     * @param name 套餐名称，查询所有填NULL，支持模糊查询
     * @param status 售卖状态， 1：起售， 0：停售，查询所有填NULL
     * @return 套餐信息
     */
    List<SetmealDO> list(@Param("categoryId") Long categoryId, @Param("name") String name, @Param("status") Integer status);

}
