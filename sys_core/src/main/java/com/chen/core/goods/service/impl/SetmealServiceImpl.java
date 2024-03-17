package com.chen.core.goods.service.impl;

import com.chen.core.goods.mapper.SetmealMapper;
import com.chen.core.goods.service.SetmealService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 套餐的信息
 */
@Service
public class SetmealServiceImpl implements SetmealService {


    /**套餐的信息*/
    @Resource
    private SetmealMapper setmealMapper;

    /**
     * 根据分类id查询套餐的数量
     * @param categoryId 分类Id
     * @return 套餐的数量
     */
    @Override
    public Integer countByCategoryId(Long categoryId) {
        return setmealMapper.countByCategoryId(categoryId);
    }

}
