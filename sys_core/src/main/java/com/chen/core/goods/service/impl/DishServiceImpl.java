package com.chen.core.goods.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.chen.common.constant.MessageConstant;
import com.chen.common.constant.StatusConstant;
import com.chen.common.exception.DeletionNotAllowedException;
import com.chen.common.result.QueryPage;
import com.chen.core.goods.bo.DishBO;
import com.chen.core.goods.entity.DishDO;
import com.chen.core.goods.entity.DishFlavorDO;
import com.chen.core.goods.entity.SetmealDishDO;
import com.chen.core.goods.mapper.DishMapper;
import com.chen.core.goods.service.DishFlavorService;
import com.chen.core.goods.service.DishService;
import com.chen.core.goods.service.SetmealDishService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    /** 套餐和菜品的关联关系 */
    @Resource
    private SetmealDishService setmealDishService;

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
     * 修改菜品信息
     * @param dishBO 菜品信息BO
     * @return 操作成功:返回true，操作失败:返回false
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean update(DishBO dishBO){
        DishDO dishDO = new DishDO();
        BeanUtil.copyProperties(dishBO, dishDO);
        boolean executeSuccess = dishMapper.update(dishDO) > 0;

        if(executeSuccess){

            List<DishFlavorDO> flavorDOList = dishFlavorService.listByDishId(dishBO.getId());
            if (CollUtil.isNotEmpty(flavorDOList)) {
                executeSuccess = dishFlavorService.deteleByDishId(dishBO.getId());
            }
        }
        if (CollUtil.isNotEmpty(dishBO.getFlavorList())){
            if(executeSuccess){
                List<DishFlavorDO> flavorList = dishBO.getFlavorList();
                flavorList.forEach(dishFlavorDO -> dishFlavorDO.setDishId(dishBO.getId()));
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
     * 根据菜品Id查询菜品信息
     * @param id 菜品Id
     * @return 菜品信息
     */
    @Override
    public DishDO getById(Long id){
        return dishMapper.getById(id);
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
     * 根据菜品id集合批量删除菜品信息
     * @param ids 菜品id集合
     * @return 操作成功:返回true，操作失败:返回false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(List<Long> ids){
        //判断当前菜品是否处于启售状态
        List<DishDO> dishDOList = dishMapper.list(null, null, null);
        for(Long id : ids){
            for(DishDO dishDO : dishDOList){
                if(id.longValue() == dishDO.getId().longValue()){
                    if(dishDO.getStatus() == StatusConstant.ENABLE){
                        throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
                    }
                }
            }
        }

        //判断当前菜品是否被套餐关联
        List<SetmealDishDO> setmealDishDOList = setmealDishService.listAll();
        for(Long id : ids){
            for(SetmealDishDO setmealDishDO : setmealDishDOList){
                if(id.longValue() == setmealDishDO.getDishId().longValue()){
                    throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
                }
            }
        }

        //删除菜品表中的菜品
        boolean executeSuccess = dishMapper.deleteByIds(ids) > 0;

        //删除菜品口味中的口味
        List<DishFlavorDO> flavorDOList = dishFlavorService.listAll();
        List<Long> deleteIds = new ArrayList<>();
        for(Long id : ids){
            for(DishFlavorDO dishFlavorDO : flavorDOList){
                if(id.longValue() == dishFlavorDO.getDishId().longValue()){
                    deleteIds.add(dishFlavorDO.getId());
                }
            }
        }
        if(executeSuccess){
            executeSuccess = dishFlavorService.deleteByIds(deleteIds);
        }

        if (!executeSuccess){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;

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
