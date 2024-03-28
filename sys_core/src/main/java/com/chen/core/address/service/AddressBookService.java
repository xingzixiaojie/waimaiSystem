package com.chen.core.address.service;

import com.chen.core.address.entity.AddressBookDO;

import java.util.List;

/** C端用户的收货地址信息 */
public interface AddressBookService {

    /**
     * 条件查询所有地址信息
     * @param userId 用户id，查询所有填NULL
     * @param phone 手机号，查询所有填NULL
     * @param isDefault 是否默认地址， 1：是，0：否 ，查询所有填NULL
     * @return
     */
    List<AddressBookDO> list(String userId, String phone, Integer isDefault);

    /**
     * 新增地址信息
     * @param addressBookDO 地址信息
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean insert(AddressBookDO addressBookDO);

    /**
     * 根据id查询地址信息
     * @param id 地址信息id
     * @return 地址信息
     */
    AddressBookDO getById(Long id);

    /**
     * 根据id修改地址信息
     * @param addressBookDO 地址信息
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean update(AddressBookDO addressBookDO);

    /**
     * 根据用户id修改默认地址
     * @Param addressBookDO 地址信息
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean setDefault(AddressBookDO addressBookDO);

    /**
     * 根据id删除地址
     * @param id 地址信息id
     * @return 操作成功:返回true，操作失败:返回false
     */
    boolean deleteById(Long id);

}
