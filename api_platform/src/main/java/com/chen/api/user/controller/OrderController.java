package com.chen.api.user.controller;

import cn.hutool.core.bean.BeanUtil;
import com.chen.api.user.po.OrderSubmitPO;
import com.chen.api.user.vo.OrderSubmitVO;
import com.chen.common.result.Result;
import com.chen.core.order.entity.OrderDO;
import com.chen.core.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "10.8 用户订单")
@RestController("UserOrderController")
@RequestMapping("/user/order")
public class OrderController {

    /** C端用户的订单数据 */
    @Resource
    private OrderService orderService;


    @ApiOperation("10.8.1 用户下单")
    @PostMapping("/submit")
    public Result<OrderSubmitVO> submit(@RequestBody OrderSubmitPO po){
        OrderDO orderDO = new OrderDO();
        BeanUtil.copyProperties(po, orderDO);
        OrderDO order = orderService.submitOrder(orderDO);
        OrderSubmitVO orderSubmitVO = new OrderSubmitVO();
        orderSubmitVO.setId(order.getId());
        orderSubmitVO.setOrderTime(order.getOrderTime());
        orderSubmitVO.setOrderNumber(order.getNumber());
        orderSubmitVO.setOrderAmount(order.getAmount());
        return Result.success(orderSubmitVO);
    }

}
