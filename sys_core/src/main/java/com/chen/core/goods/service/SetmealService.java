package com.chen.core.goods.service;

import com.chen.common.result.QueryPage;
import com.chen.core.goods.bo.SetmealBO;
import com.chen.core.goods.entity.SetmealDO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 套餐的信息
 */
public interface SetmealService {

    /**
     * 新增套餐信息
     * @param setmealBO 套餐信息
     * @return 操作成功:返回true，操作失败:返回false
     */

    boolean insert(SetmealBO setmealBO);

    /**
     * 修改套餐信息
     * @param setmealBO 套餐信息
     * @return 操作成功:返回true，操作失败:返回false
     */

    boolean update(SetmealBO setmealBO);

    /**
     * 修改套餐售卖状态
     * @param id 套餐Id
     * @param status 售卖状态， 1：起售， 0：停售
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean updateStatus(Long id, Integer status);


    /**
     * 根据套餐id集合批量删除套餐信息
     * @param ids 套餐id集合
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean deleteBatchByIds(List<Long> ids);

    /**
     * 根据分类id查询套餐的数量
     * @param categoryId 分类Id
     * @return 套餐的数量
     */
    Integer countByCategoryId(Long categoryId);

    /**
     * 根据套餐id查询套餐信息
     * @param id 套餐id
     * @return 套餐信息
     */
    SetmealDO getById(Long id);

    /**
     * 获取所有套餐信息
     * @Param page 分页对象
     * @param categoryId 菜品分类id，查询所有填NULL
     * @param name 套餐名称，查询所有填NULL，支持模糊查询
     * @param status 售卖状态， 1：起售， 0：停售，查询所有填NULL
     * @return 套餐分页集合
     */
    PageInfo<SetmealDO> list(QueryPage page, Long categoryId, String name, Integer status);

}
