package com.chen.core.order.mapper;

import com.chen.core.order.entity.OrderDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * C端用户的订单数据
 */
@Mapper
public interface OrdersMapper {

    /**
     * 新增订单数据
     * @param orderDO 订单信息
     * @return 影响条数
     */
    int insert(OrderDO orderDO);

}
