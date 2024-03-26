package com.chen.core.goods.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.chen.common.result.QueryPage;
import com.chen.core.goods.bo.SetmealBO;
import com.chen.core.goods.entity.SetmealDO;
import com.chen.core.goods.entity.SetmealDishDO;
import com.chen.core.goods.mapper.SetmealMapper;
import com.chen.core.goods.service.SetmealDishService;
import com.chen.core.goods.service.SetmealService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * 套餐的信息
 */
@Service
public class SetmealServiceImpl implements SetmealService {

    /**套餐的信息*/
    @Resource
    private SetmealMapper setmealMapper;

    /** 套餐和菜品的关联关系 */
    @Resource
    private SetmealDishService setmealDishService;

    /**
     * 新增套餐信息
     * @param setmealBO 套餐信息
     * @return 操作成功:返回true，操作失败:返回false
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(SetmealBO setmealBO){
        SetmealDO setmealDO = new SetmealDO();
        BeanUtil.copyProperties(setmealBO, setmealDO);
        boolean executeSuccess = setmealMapper.insert(setmealDO) > 0;

        if(executeSuccess){
            if(CollUtil.isNotEmpty(setmealBO.getSetmealDishes())){
                List<SetmealDishDO> setmealDishDOList = setmealBO.getSetmealDishes();
                setmealDishDOList.forEach(setmealDishDO -> setmealDishDO.setSetmealId(setmealDO.getId()));
                executeSuccess  = setmealDishService.insertBatch(setmealDishDOList);
            }
        }

        if (!executeSuccess){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    /**
     * 修改套餐信息
     * @param setmealBO 套餐信息
     * @return 操作成功:返回true，操作失败:返回false
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(SetmealBO setmealBO){
        SetmealDO setmealDO = new SetmealDO();
        BeanUtil.copyProperties(setmealBO, setmealDO);
        boolean executeSuccess = setmealMapper.update(setmealDO) > 0;

        if(executeSuccess){
            List<SetmealDishDO> setmealDishDOList = setmealDishService.listBySetmealId(setmealBO.getId());
            if(CollUtil.isNotEmpty(setmealDishDOList)){
                List<Long> deleteId = new ArrayList<>();
                deleteId.add(setmealBO.getId());
                executeSuccess = setmealDishService.deleteBatchBySetmealIds(deleteId);
            }
        }
        if(CollUtil.isNotEmpty(setmealBO.getSetmealDishes())){
            if(executeSuccess){
                List<SetmealDishDO> setmealDishDOList = setmealBO.getSetmealDishes();
                setmealDishDOList.forEach(setmealDishDO -> setmealDishDO.setSetmealId(setmealBO.getId()));
                executeSuccess = setmealDishService.insertBatch(setmealDishDOList);
            }
        }

        if (!executeSuccess){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    /**
     * 修改套餐售卖状态
     * @param id 套餐Id
     * @param status 售卖状态， 1：起售， 0：停售
     * @return 操作成功:返回true，操作失败:返回false
     */
    @Override
    public boolean updateStatus(Long id, Integer status){
        return setmealMapper.updateStatus(id, status) > 0;
    }

    /**
     * 根据套餐id集合批量删除套餐信息
     * @param ids 套餐id集合
     * @return 操作成功:返回true，操作失败:返回false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatchByIds(List<Long> ids){
        boolean executeSuccess = setmealMapper.deleteBatchByIds(ids) > 0;
        if(executeSuccess){
            List<SetmealDishDO> setmealDishDOList = setmealDishService.listAll();
            if(CollUtil.isNotEmpty(setmealDishDOList)){

                List<Long> deleteSetMealId = new ArrayList<>();
                for(Long id : ids){
                    for(SetmealDishDO setmealDishDO : setmealDishDOList){
                        if(id.longValue() == setmealDishDO.getSetmealId().longValue()){
                            deleteSetMealId.add(id);
                        }
                    }
                }

                if(CollUtil.isNotEmpty(deleteSetMealId)){
                    executeSuccess = setmealDishService.deleteBatchBySetmealIds(deleteSetMealId);
                }
            }
        }

        if (!executeSuccess){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    /**
     * 根据分类id查询套餐的数量
     * @param categoryId 分类Id
     * @return 套餐的数量
     */
    @Override
    public Integer countByCategoryId(Long categoryId) {
        return setmealMapper.countByCategoryId(categoryId);
    }

    /**
     * 根据套餐id查询套餐信息
     * @param id 套餐id
     * @return 套餐信息
     */
    @Override
    public SetmealDO getById(Long id){
        return setmealMapper.getById(id);
    }

    /**
     * 获取所有套餐信息
     * @Param page 分页对象
     * @param categoryId 菜品分类id，查询所有填NULL
     * @param name 套餐名称，支持模糊查询, 查询所有填NULL
     * @param status 售卖状态， 1：起售， 0：停售，查询所有填NULL
     * @return 套餐分页集合
     */
    @Override
    public PageInfo<SetmealDO> list(QueryPage page, Long categoryId, String name, Integer status){
        if(page != null){
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }
        return new PageInfo<>(setmealMapper.list(categoryId, name, status));
    }

}
