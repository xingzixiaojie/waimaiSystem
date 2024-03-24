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
     * 批量插入套餐菜品关系信息
     * @param setmealDishDOList 套餐菜品关系信息集合
     * @return 操作成功:返回true，操作失败:返回false
     */
    @Override
    public boolean insertBatch(List<SetmealDishDO> setmealDishDOList){
        return setmealDishMapper.insertBatch(setmealDishDOList) > 0;
    }

    /**
     * 根据套餐id集合批量删除套餐菜品关系信息
     * @param setmealIds 套餐id集合
     * @return 操作成功:返回true，操作失败:返回false
     */
    @Override
    public boolean deleteBatchBySetmealIds(List<Long> setmealIds){
        return setmealDishMapper.deleteBatchBySetmealIds(setmealIds) > 0;
    }

    /**
     * 根据套餐id查询套餐菜品关联信息
     * @param setmealId 套餐id
     * @return 套餐和菜品关联信息
     */
    @Override
    public List<SetmealDishDO> listBySetmealId(Long setmealId){
        return setmealDishMapper.listBySetmealId(setmealId);
    }

    /**
     * 获取所有的套餐和菜品关联信息
     * @return 套餐和菜品关联信息
     */
    @Override
    public List<SetmealDishDO> listAll(){
        return setmealDishMapper.listAll();
    }

}
