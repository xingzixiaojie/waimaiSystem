package com.chen.core.order.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.chen.common.constant.MessageConstant;
import com.chen.common.context.BaseContext;
import com.chen.common.exception.AddressBookBusinessException;
import com.chen.common.exception.ShoppingCartBusinessException;
import com.chen.common.utils.DateUtil;
import com.chen.core.address.entity.AddressBookDO;
import com.chen.core.address.service.AddressBookService;
import com.chen.core.order.entity.OrderDO;
import com.chen.core.order.entity.OrderDetailDO;
import com.chen.core.order.entity.ShoppingCartDO;
import com.chen.core.order.mapper.OrdersMapper;
import com.chen.core.order.service.OrderDetailService;
import com.chen.core.order.service.OrderService;
import com.chen.core.order.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * C端用户的订单数据
 */
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * C端用户的订单数据
     */
    @Resource
    private OrdersMapper ordersMapper;

    /** C端用户的订单明细数据 */
    @Resource
    private OrderDetailService orderDetailService;

    /** 用户端购物车 */
    @Resource
    private ShoppingCartService shoppingCartService;

    /** C端用户的收货地址信息 */
    @Resource
    private AddressBookService addressBookService;

    /**
     * 用户下单
     * @param orderDO 订单信息
     * @return 操作成功:返回true，操作失败:返回false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderDO submitOrder(OrderDO orderDO) {
        Long currentId = BaseContext.getCurrentId();

        AddressBookDO addressBookDO = addressBookService.getById(orderDO.getAddressBookId());
        boolean executeSuccess = false;
        if(addressBookDO == null){
            throw new AddressBookBusinessException(MessageConstant.ADDRESS_BOOK_IS_NULL);
        }

        List<ShoppingCartDO> shoppingCartDOList = shoppingCartService.list(currentId, null, null, null);
        if(CollUtil.isEmpty(shoppingCartDOList)){
            throw new ShoppingCartBusinessException(MessageConstant.SHOPPING_CART_IS_NULL);
        }

        orderDO.setOrderTime(DateUtil.getCurTime());
        orderDO.setStatus(1);
        orderDO.setPayStatus(0);
        orderDO.setNumber(String.valueOf(System.currentTimeMillis()));
        orderDO.setPhone(addressBookDO.getPhone());
        orderDO.setConsignee(addressBookDO.getConsignee());
        orderDO.setUserId(currentId);
        executeSuccess = ordersMapper.insert(orderDO) > 0;

        List<OrderDetailDO> orderDetailDOList = new ArrayList<>();
        for(ShoppingCartDO shoppingCartDO : shoppingCartDOList){
            OrderDetailDO orderDetailDO = new OrderDetailDO();
            BeanUtil.copyProperties(shoppingCartDO, orderDetailDO);
            orderDetailDO.setOrderId(orderDO.getId());
            orderDetailDO.setImage(shoppingCartDO.getImage());
            orderDetailDO.setId(null);
            orderDetailDOList.add(orderDetailDO);
        }
        if(executeSuccess){
            executeSuccess = orderDetailService.insertBatch(orderDetailDOList);
        }

        if(executeSuccess){
            executeSuccess = shoppingCartService.deleteByUserId(currentId);
        }

        if (!executeSuccess){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return orderDO;
    }

}
