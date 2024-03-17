package com.chen.core.goods.service.impl;

import com.chen.core.goods.mapper.DishMapper;
import com.chen.core.goods.service.DishService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 菜品的信息
 */
@Service
public class DishServiceImpl implements DishService {

    /** 菜品的信息 */
    @Resource
    private DishMapper dishMapper;

    /**
     * 根据分类id查询菜品数量
     * @param categoryId 分类Id
     * @return 菜品数量
     */
    @Override
    public Integer countByCategoryId(Long categoryId){
        return dishMapper.countByCategoryId(categoryId);
    }

}
