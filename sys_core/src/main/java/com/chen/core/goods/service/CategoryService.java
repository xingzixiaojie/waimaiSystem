package com.chen.core.goods.service;

import com.chen.common.result.QueryPage;
import com.chen.core.goods.entity.CategoryDO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 商品的分类信息
 */
public interface CategoryService {

    /**
     * 新增分类
     * @param categoryDO 分类信息
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean save(CategoryDO categoryDO);


    /**
     * 修改分类
     * @param categoryDO 分类信息
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean update(CategoryDO categoryDO);

    /**
     * 启用、禁用分类
     * @param status 状态,1：启用，0：禁用，查询全部填NULL
     * @param id 分类id
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean startOrStop(Integer status, Long id);

    /**
     * 根据id删除分类
     * @param id 分类id
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean deleteById(Long id);


    /**
     * 查询分类信息
     * @param page 分页对象
     * @param name 分类名称，查询全部填NULL，支持模糊查询
     * @param type 分类类型，查询全部填NULL
     * @param status  状态,1：启用，0：禁用，查询全部填NULL
     * @return 分类信息集合
     */
    PageInfo<CategoryDO> pageQuery(QueryPage page, String name, Integer type, Integer status);

    /**
     * 查询所有分类信息
     * @return 分类信息集合
     */
    List<CategoryDO> listAll();

}
