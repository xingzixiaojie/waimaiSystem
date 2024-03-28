package com.chen.core.order.service;

import com.chen.core.order.entity.OrderDO;

/**
 * C端用户的订单数据
 */
public interface OrderService {

    /**
     * 用户下单
     * @param orderDO 订单信息
     * @return 操作成功:返回true，操作失败:返回false
     */
    OrderDO submitOrder(OrderDO orderDO);
}
