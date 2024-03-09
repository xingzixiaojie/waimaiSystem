package com.chen.core.order.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/** C端用户的订单数据 */
@Data
public class OrdersDO implements Serializable {

    /** 主键，自增 */
    private Long id;

    /** 订单号 */
    private String number;

    /**  订单状态, 1：待付款，2：待接单，3：已接单，4：派送中，5：已完成，6：已取消*/
    private Integer status;

    /** 用户id  */
    private Long userId;

    /** 地址id */
    private Long addressBookId;

    /** 下单时间 */
    private Timestamp orderTime;

    /** 付款时间 */
    private Timestamp checkoutTime;

    /** 支付方式, 1：微信支付，2：支付宝支付 */
    private Integer pay_method;

    /** 支付状态, 0：未支付，1：已支付，2：退款 */
    private Integer payStatus;

    /** 订单金额 */
    private BigDecimal amount;

    /** 备注信息 */
    private String remark;

    /** 手机号 */
    private String phone;

    /** 详细地址信息 */
    private String address;

    /** 用户姓名 */
    private String userName;

    /** 收货人 */
    private String consignee;

    /** 订单取消原因 */
    private String cancelReason;

    /** 拒单原因 */
    private String rejectionReason;

    /** 订单取消时间 */
    private Timestamp cancelTime;

    /** 预计送达时间 */
    private Timestamp estimatedDeliveryTime;

    /** 配送状态, 1：立即送出 ，0：选择具体时间 */
    private Integer deliveryStatus;

    /** 送达时间 */
    private Timestamp deliveryTime;

    /** 打包费  */
    private Integer packAmount;

    /** 餐具数量 */
    private Integer tablewareNumber;

    /** 餐具数量状态, 1：按餐量提供 ，0：选择具体数量 */
    private Integer tablewareStatus;

}
