package com.chen.core.goods.service.impl;

import com.chen.core.goods.entity.SetmealDishDO;
import com.chen.core.goods.mapper.SetmealDishMapper;
import com.chen.core.goods.service.SetmealDishService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/** 套餐和菜品的关联关系 */
@Service
public class SetmealDishServiceImpl implements SetmealDishService {

    /** 套餐和菜品的关联关系 */
    @Resource
    private SetmealDishMapper setmealDishMapper;

    /**
     * 获取所有的套餐和菜品关联信息
     * @return 套餐和菜品关联信息
     */
    @Override
    public List<SetmealDishDO> listAll(){
        return setmealDishMapper.listAll();
    }

}
