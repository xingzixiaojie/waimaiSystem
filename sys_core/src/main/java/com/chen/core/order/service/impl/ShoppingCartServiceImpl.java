package com.chen.core.order.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.chen.common.context.BaseContext;
import com.chen.common.utils.DateUtil;
import com.chen.core.goods.entity.DishDO;
import com.chen.core.goods.entity.SetmealDO;
import com.chen.core.goods.service.DishService;
import com.chen.core.goods.service.SetmealService;
import com.chen.core.order.entity.ShoppingCartDO;
import com.chen.core.order.mapper.ShoppingCartMapper;
import com.chen.core.order.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/** 用户端购物车 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    /** C端用户的购物车信息 */
    @Resource
    private ShoppingCartMapper shoppingCartMapper;

    /** 菜品的信息 */
    @Resource
    private DishService dishService;

    /**套餐的信息*/
    @Resource
    private SetmealService setmealService;

    /**
     * 添加商品到购物车
     * @param shoppingCartDO 商品购物车信息
     * @return 操作成功:返回true，操作失败:返回false
     */
    @Override
    public boolean addShoppingCart(ShoppingCartDO shoppingCartDO) {
        shoppingCartDO.setUserId(BaseContext.getCurrentId());
        List<ShoppingCartDO> shoppingCartDOList = this.list(shoppingCartDO.getUserId(), shoppingCartDO.getDishId(),
                                                            shoppingCartDO.getSetmealId(), shoppingCartDO.getDishFlavor());
        if(CollUtil.isNotEmpty(shoppingCartDOList)){
            ShoppingCartDO cartDO = shoppingCartDOList.get(0);
            cartDO.setNumber(cartDO.getNumber() + 1);
            boolean flag = this.updateNumberById(cartDO.getId(), cartDO.getNumber());
            return flag;
        }else {
            Long dishId = shoppingCartDO.getDishId();
            if(dishId != null){
                DishDO dishDO = dishService.getById(dishId);
                shoppingCartDO.setName(dishDO.getName());
                shoppingCartDO.setImage(dishDO.getImage());
                shoppingCartDO.setAmount(dishDO.getPrice());
            }else {
                SetmealDO setmealDO = setmealService.getById(shoppingCartDO.getSetmealId());
                shoppingCartDO.setName(setmealDO.getName());
                shoppingCartDO.setImage(setmealDO.getImage());
                shoppingCartDO.setAmount(setmealDO.getPrice());
            }
            shoppingCartDO.setNumber(1);
            shoppingCartDO.setCreateTime(DateUtil.getCurTime());
            boolean flag = shoppingCartMapper.insert(shoppingCartDO) > 0 ;
            return flag;
        }
    }

    /**
     * 根据id修改商品数量
     * @param id 购物车商品Id
     * @param number 商品数量
     * @return 操作成功:返回true，操作失败:返回false
     */
    @Override
    public boolean updateNumberById(Long id, Integer number){
        return shoppingCartMapper.updateNumberById(id, number) > 0;
    }

    /**
     * 减少商品数量，减少到0删除商品
     * @param shoppingCartDO 商品购物车信息
     * @return 操作成功:返回true，操作失败:返回false
     */
    @Override
    public boolean subShoppingNumber(ShoppingCartDO shoppingCartDO){
        shoppingCartDO.setUserId(BaseContext.getCurrentId());
        List<ShoppingCartDO> shoppingCartDOList = this.list(shoppingCartDO.getUserId(), shoppingCartDO.getDishId(),
                shoppingCartDO.getSetmealId(), shoppingCartDO.getDishFlavor());
        if(CollUtil.isNotEmpty(shoppingCartDOList)){
            ShoppingCartDO cartDO = shoppingCartDOList.get(0);
            cartDO.setNumber(cartDO.getNumber() - 1);
            boolean flag = false;
            if (cartDO.getNumber() == 0) {
                flag = shoppingCartMapper.deleteById(cartDO.getId()) > 0;
            }else {
                flag = this.updateNumberById(cartDO.getId(), cartDO.getNumber());
            }
            return flag;
        }else {
            return false;
        }
    }

    /**
     * 根据用户id删除所有商品
     * @param userId 用户id
     * @return 操作成功:返回true，操作失败:返回false
     */
    @Override
    public boolean deleteByUserId(Long userId){
        return shoppingCartMapper.deleteByUserId(userId) > 0;
    }

    /**
     * 查询购物车里的全部商品信息
     * @param userId 用户id， 填null查询全部商品信息
     * @param dishId 菜品id， 填null查询全部商品信息
     * @param setmealId 套餐id， 填null查询全部商品信息
     * @param dishFlavor 菜品口味， 填null查询全部商品信息
     * @return 购物车里的商品信息集合
     */
    @Override
    public List<ShoppingCartDO> list(Long userId, Long dishId, Long setmealId, String dishFlavor){
        return shoppingCartMapper.list(userId, dishId, setmealId, dishFlavor);
    }

}
