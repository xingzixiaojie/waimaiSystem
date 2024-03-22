package com.chen.core.goods.service.impl;

import com.chen.common.constant.MessageConstant;
import com.chen.common.constant.StatusConstant;
import com.chen.common.context.BaseContext;
import com.chen.common.exception.DeletionNotAllowedException;
import com.chen.common.result.QueryPage;
import com.chen.common.utils.DateUtil;
import com.chen.core.goods.entity.CategoryDO;
import com.chen.core.goods.mapper.CategoryMapper;
import com.chen.core.goods.service.CategoryService;
import com.chen.core.goods.service.DishService;
import com.chen.core.goods.service.SetmealService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品的分类信息
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    /** 商品的分类信息 */
    @Resource
    private CategoryMapper categoryMapper;

    /** 菜品的信息 */
    @Resource
    private DishService dishService;

    /**套餐的信息*/
    @Resource
    private SetmealService setmealService;

    /**
     * 新增分类
     * @param categoryDO 分类信息
     * @return 操作成功:返回true，操作失败:返回false
     */
    @Override
    public boolean save(CategoryDO categoryDO){
        //分类状态默认为禁用状态0
        categoryDO.setStatus(StatusConstant.DISABLE);

        //设置创建时间、修改时间、创建人、修改人
        categoryDO.setCreateTime(DateUtil.getCurTime());
        categoryDO.setUpdateTime(DateUtil.getCurTime());
        categoryDO.setCreateUser(BaseContext.getCurrentId());
        categoryDO.setUpdateUser(BaseContext.getCurrentId());

        return categoryMapper.insertCategory(categoryDO) > 0;
    }


    /**
     * 修改分类
     * @param categoryDO 分类信息
     * @return 操作成功:返回true，操作失败:返回false
     */
    @Override
    public boolean update(CategoryDO categoryDO){
        //设置修改时间、修改人
        categoryDO.setUpdateTime(DateUtil.getCurTime());
        categoryDO.setUpdateUser(BaseContext.getCurrentId());
        return categoryMapper.updateCategory(categoryDO) > 0;
    }

    /**
     * 启用、禁用分类
     * @param status 状态,1：启用，0：禁用，查询全部填NULL
     * @param id 分类id
     * @return 操作成功:返回true，操作失败:返回false
     */
    @Override
    public boolean startOrStop(Integer status, Long id){
        CategoryDO categoryDO = new CategoryDO();
        categoryDO.setId(id);
        categoryDO.setStatus(status);
        categoryDO.setUpdateTime(DateUtil.getCurTime());
        categoryDO.setUpdateUser(BaseContext.getCurrentId());
        return categoryMapper.updateCategory(categoryDO) > 0;
    }

    /**
     * 根据id删除分类
     * @param id 分类id
     * @return 操作成功:返回true，操作失败:返回false
     */
    @Override
    public boolean deleteById(Long id){

        //查询当前分类是否关联了菜品，如果关联了就抛出业务异常
        Integer count = dishService.countByCategoryId(id);
        if(count > 0){
            //当前分类下有菜品，不能删除
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_DISH);
        }

        //查询当前分类是否关联了套餐，如果关联了就抛出业务异常
        count = setmealService.countByCategoryId(id);
        if(count > 0){
            //当前分类下有菜品，不能删除
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_SETMEAL);
        }

        //删除分类数据
        return categoryMapper.deleteCategory(id) > 0;
    }


    /**
     * 查询分类信息
     * @param page 分页对象
     * @param name 分类名称，查询全部填NULL，支持模糊查询
     * @param type 分类类型，查询全部填NULL
     * @param status  状态,1：启用，0：禁用，查询全部填NULL
     * @return 分类信息集合
     */
    @Override
    public PageInfo<CategoryDO> pageQuery(QueryPage page, String name, Integer type, Integer status){
        if(page != null){
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }
        return new PageInfo<>(categoryMapper.listCategory(name, type, status));
    }

    /**
     * 查询所有分类信息
     * @return 分类信息集合
     */
    @Override
    public List<CategoryDO> listAll(){
        return categoryMapper.listAll();
    }

}
