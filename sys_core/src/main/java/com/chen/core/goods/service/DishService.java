package com.chen.core.goods.service;

import com.chen.common.result.QueryPage;
import com.chen.core.goods.bo.DishBO;
import com.chen.core.goods.entity.DishDO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 菜品的信息
 */
public interface DishService {

    /**
     * 新增菜品和口味信息
     * @param dishBO 菜品和口味的信息
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean insertDish(DishBO dishBO);

    /**
     * 修改菜品信息
     * @param dishBO 菜品信息BO
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean update(DishBO dishBO);

    /**
     * 修改菜品售卖状态
     * @param status 售卖状态， 1：起售， 0：停售
     * @param id 主键
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean updateStatus(Integer status, Long id);

    /**
     * 根据分类id查询菜品数量
     * @param categoryId 分类Id
     * @return 菜品数量
     */
    Integer countByCategoryId(Long categoryId);

    /**
     * 根据菜品Id查询菜品信息
     * @param id 菜品Id
     * @return 菜品信息
     */
    DishDO getById(Long id);

    /**
     * 根据菜品名称获取菜品信息
     * @param name 菜品名称
     * @return 菜品信息
     */
    DishDO getByName(String name);

    /**
     * 根据菜品id集合批量删除菜品信息
     * @param ids 菜品id集合
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean deleteBatch(List<Long> ids);

    /**
     * 根据菜品分类id查询菜品信息
     * @param categoryId 菜品分类id
     * @return 菜品信息集合
     */
    List<DishDO> listByCategoryId(Long categoryId);

    /**
     * 查询全部菜品信息
     * @param page 分页信息
     * @param name 菜品名称，支持模糊查询，查询全部填NULL
     * @param categoryId 菜品分类id，查询全部填NULL
     * @param status 售卖状态，1：起售， 0：停售, 查询全部填NULL
     * @return 菜品信息集合
     */
    PageInfo<DishDO> list(QueryPage page, String name, Long categoryId, Integer status);

}
