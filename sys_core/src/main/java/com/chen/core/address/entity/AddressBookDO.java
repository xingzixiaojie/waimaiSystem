package com.chen.core.address.entity;

import lombok.Data;

import java.io.Serializable;

/** C端用户的收货地址信息 */
@Data
public class AddressBookDO implements Serializable {

    /** 主键，自增 */
    private Long id;

    /** 用户id */
    private String userId;

    /** 收货人 */
    private String consignee;

    /** 性别 */
    private String sex;

    /** 手机号 */
    private String phone;

    /** 省份编码 */
    private String provinceCode;

    /** 省份名称 */
    private String provinceName;

    /** 城市编码 */
    private String cityCode;

    /** 城市名称 */
    private String cityName;

    /** 区县编码 */
    private String districtCode;

    /** 区县名称 */
    private String districtName;

    /** 详细地址信息, 具体到门牌号 */
    private String detail;

    /** 标签, 公司、家、学校 */
    private String label;

    /** 是否默认地址, 1：是，0：否    */
    private Integer isDefault;

}
