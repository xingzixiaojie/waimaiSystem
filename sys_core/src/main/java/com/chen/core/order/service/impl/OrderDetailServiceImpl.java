package com.chen.core.order.service.impl;

import com.chen.core.order.entity.OrderDetailDO;
import com.chen.core.order.mapper.OrderDetailMapper;
import com.chen.core.order.service.OrderDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/** C端用户的订单明细数据 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    /** C端用户的订单明细数据 */
    @Resource
    private OrderDetailMapper orderDetailMapper;

    /**
     * 批量插入订单明细数据
     * @param orderDetailDOList 订单明细数据集合
     * @return 操作成功:返回true，操作失败:返回false
     */
    @Override
    public boolean insertBatch(List<OrderDetailDO> orderDetailDOList) {
        return orderDetailMapper.insertBatch(orderDetailDOList) > 0;
    }

}
