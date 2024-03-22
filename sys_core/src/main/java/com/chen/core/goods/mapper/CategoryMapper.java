package com.chen.core.goods.mapper;

import com.chen.core.goods.entity.CategoryDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品的分类信息
 */
@Mapper
public interface CategoryMapper {


    /**
     * 新增商品的分类信息
     * @param categoryDO 分类信息
     * @return 影响条数
     */
    int  insertCategory(CategoryDO categoryDO);

    /**
     * 根据id修改分类
     * @param categoryDO 分类信息
     * @return 影响条数
     */
    int updateCategory(CategoryDO categoryDO);

    /**
     * 根据id删除分类
     * @param id 主键
     * @return 影响条数
     */
    int deleteCategory(@Param("id") Long id);

    /**
     * 根据id获取分类信息
     * @param id 主键
     * @return 分类信息
     */
    CategoryDO getById(@Param("id") Long id);

    /**
     * 查询分类信息
     * @param name 分类名称，查询全部填NULL，支持模糊查询
     * @param type 分类类型，查询全部填NULL
     * @Param status 状态,1：启用，0：禁用，查询全部填NULL
     * @return 分类信息集合
     */
    List<CategoryDO> listCategory(@Param("name") String name, @Param("type") Integer type, @Param("status") Integer status);

    /**
     * 查询所有分类信息
     * @return 分类信息集合
     */
    List<CategoryDO> listAll();
}
