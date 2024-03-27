package com.chen.core.order.service;

import com.chen.core.order.entity.ShoppingCartDO;

import java.util.List;

/** 用户端购物车 */
public interface ShoppingCartService {

    /**
     * 添加商品到购物车
     * @param shoppingCartDO 商品购物车信息
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean addShoppingCart(ShoppingCartDO shoppingCartDO);

    /**
     * 根据id修改商品数量
     * @param id 购物车商品Id
     * @param number 商品数量
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean updateNumberById(Long id, Integer number);

    /**
     * 减少商品数量，减少到0删除商品
     * @param shoppingCartDO 商品购物车信息
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean subShoppingNumber(ShoppingCartDO shoppingCartDO);

    /**
     * 根据用户id删除所有商品
     * @param userId 用户id
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean deleteByUserId(Long userId);

    /**
     * 查询购物车里的全部商品信息
     * @param userId 用户id， 填null查询全部商品信息
     * @param dishId 菜品id， 填null查询全部商品信息
     * @param setmealId 套餐id， 填null查询全部商品信息
     * @param dishFlavor 菜品口味， 填null查询全部商品信息
     * @return 购物车里的商品信息集合
     */
    List<ShoppingCartDO> list(Long userId, Long dishId, Long setmealId, String dishFlavor);

}
