package com.chen.core.order.mapper;

import com.chen.core.order.entity.OrderDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * C端用户的订单明细数据
 */
@Mapper
public interface OrderDetailMapper {

    /**
     * 批量插入订单明细数据
     * @param list 订单明细数据集合
     * @return 影响条数
     */
    int insertBatch(@Param("list") List<OrderDetailDO> list);

}
