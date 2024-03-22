package com.chen.core.goods.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.chen.common.result.QueryPage;
import com.chen.core.goods.bo.DishBO;
import com.chen.core.goods.entity.DishDO;
import com.chen.core.goods.entity.DishFlavorDO;
import com.chen.core.goods.mapper.DishMapper;
import com.chen.core.goods.service.DishFlavorService;
import com.chen.core.goods.service.DishService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜品的信息
 */
@Service
public class DishServiceImpl implements DishService {

    /** 菜品的信息 */
    @Resource
    private DishMapper dishMapper;

    /** 菜品的口味信息 */
    @Resource
    private DishFlavorService dishFlavorService;

    /**
     * 新增菜品和口味信息
     * @param dishBO 菜品和口味的信息
     * @return 操作成功:返回true，操作失败:返回false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertDish(DishBO dishBO) {
        DishDO dishDO = new DishDO();
        BeanUtil.copyProperties(dishBO, dishDO);
        boolean executeSuccess = dishMapper.insert(dishDO) > 0;

        if(executeSuccess){
            if(CollUtil.isNotEmpty(dishBO.getFlavorList())){
                List<DishFlavorDO> flavorList = dishBO.getFlavorList();
                flavorList.forEach(dishFlavorDO -> dishFlavorDO.setDishId(dishDO.getId()));
                executeSuccess = dishFlavorService.insertDishFlavor(flavorList);
            }
        }

        if (!executeSuccess){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    /**
     * 根据分类id查询菜品数量
     * @param categoryId 分类Id
     * @return 菜品数量
     */
    @Override
    public Integer countByCategoryId(Long categoryId){
        return dishMapper.countByCategoryId(categoryId);
    }

    /**
     * 根据菜品名称获取菜品信息
     * @param name 菜品名称
     * @return 菜品信息
     */
    @Override
    public DishDO getByName(String name){
        return dishMapper.getByName(name);
    }

    /**
     * 查询全部菜品信息
     * @param page 分页信息
     * @param name 菜品名称，支持模糊查询，查询全部填NULL
     * @param categoryId 菜品分类id，查询全部填NULL
     * @param status 售卖状态，1：起售， 0：停售, 查询全部填NULL
     * @return 菜品信息集合
     */
    @Override
    public PageInfo<DishDO> list(QueryPage page, String name, Long categoryId, Integer status){
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return new PageInfo<>(dishMapper.list(name, categoryId, status));
    }

}