package com.chen.core.order.service;

import com.chen.core.order.entity.OrderDetailDO;

import java.util.List;

/** C端用户的订单明细数据 */
public interface OrderDetailService {

    /**
     * 批量插入订单明细数据
     * @param orderDetailDOList 订单明细数据集合
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean insertBatch(List<OrderDetailDO> orderDetailDOList);

}
