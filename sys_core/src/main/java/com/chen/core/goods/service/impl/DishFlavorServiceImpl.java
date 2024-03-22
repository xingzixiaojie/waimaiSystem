package com.chen.core.goods.service.impl;

import com.chen.core.goods.entity.DishFlavorDO;
import com.chen.core.goods.mapper.DishFlavorMapper;
import com.chen.core.goods.service.DishFlavorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/** 菜品的口味信息 */
@Service
public class DishFlavorServiceImpl implements DishFlavorService {

    /** 菜品的口味信息 */
    @Resource
    private DishFlavorMapper dishFlavorMapper;

    /**
     * 新增菜品口味信息
     * @param dishFlavorDO 菜品的口味信息
     * @return 操作成功:返回true，操作失败:返回false
     */
    @Override
    public boolean insertDishFlavor(DishFlavorDO dishFlavorDO) {
        return dishFlavorMapper.insert(dishFlavorDO) > 0;
    }

    /**
     * 批量新增菜品口味信息
     * @param dishFlavorDOList 菜品的口味信息集合
     * @return 操作成功:返回true，操作失败:返回false
     */
    @Override
    public boolean insertDishFlavor(List<DishFlavorDO> dishFlavorDOList) {
        return dishFlavorMapper.insertList(dishFlavorDOList) > 0;
    }

    /**
     * 根据菜品口味id集合批量删除菜品口味信息
     * @param ids 菜品口味id集合
     * @return 操作成功:返回true，操作失败:返回false
     */
    @Override
    public boolean deleteByIds(List<Long> ids){
        return dishFlavorMapper.deleteByIds(ids) > 0;
    }

    /**
     * 根据菜品id删除菜品口味
     * @param dishId 菜品id
     * @return 操作成功:返回true，操作失败:返回false
     */
    @Override
    public boolean deteleByDishId(Long dishId){
        return dishFlavorMapper.deteleByDishId(dishId) > 0;
    }

    /**
     * 根据菜品id查询菜品口味信息
     * @param dishId 品id
     * @return 菜品口味信息集合
     */
    @Override
    public List<DishFlavorDO> listByDishId(Long dishId){
        return dishFlavorMapper.listByDishId(dishId);
    }

    /**
     * 获取所有菜品口味信息
     * @return 菜品口味信息集合
     */
    @Override
    public List<DishFlavorDO> listAll(){
        return dishFlavorMapper.listAll();
    }

}
