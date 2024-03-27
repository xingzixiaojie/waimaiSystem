package com.chen.core.order.mapper;

import com.chen.core.order.entity.ShoppingCartDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * C端用户的购物车信息
 */
@Mapper
public interface ShoppingCartMapper {

    /**
     * 新增购物车里的商品
     * @param shoppingCartDO  购物车商品信息
     * @return 影响条数
     */
    int insert(ShoppingCartDO shoppingCartDO);

    /**
     * 根据id修改商品数量
     * @param id 购物车商品Id
     * @param number 商品数量
     * @return 影响条数
     */
    int updateNumberById(@Param("id") Long id, @Param("number") Integer number);

    /**
     * 根据用户id删除所有商品
     * @param userId 用户id
     * @return 影响条数
     */
    int deleteByUserId(@Param("userId") Long userId);

    /**
     * 根据id删除商品
     * @param id 商品id
     * @return 影响条数
     */
    int deleteById(@Param("id") Long id);

    /**
     * 查询购物车里的全部商品信息
     * @param userId 用户id， 填null查询全部商品信息
     * @param dishId 菜品id， 填null查询全部商品信息
     * @param setmealId 套餐id， 填null查询全部商品信息
     * @param dishFlavor 菜品口味， 填null查询全部商品信息
     * @return 购物车里的商品信息集合
     */
    List<ShoppingCartDO> list(@Param("userId") Long userId, @Param("dishId") Long dishId, @Param("setmealId") Long setmealId,
                              @Param("dishFlavor") String dishFlavor);

}
