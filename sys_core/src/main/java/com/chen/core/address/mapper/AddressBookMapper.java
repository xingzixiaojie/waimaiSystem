package com.chen.core.address.mapper;

import com.chen.core.address.entity.AddressBookDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * C端用户的收货地址信息
 */
@Mapper
public interface AddressBookMapper {

    /**
     * 条件查询所有地址信息
     * @param userId 用户id，查询所有填NULL
     * @param phone 手机号，查询所有填NULL
     * @param isDefault 是否默认地址， 1：是，0：否 ，查询所有填NULL
     * @return
     */
    List<AddressBookDO> list(@Param("userId") String userId, @Param("phone") String phone, @Param("isDefault") Integer isDefault);

    /**
     * 新增地址信息
     * @param addressBookDO 地址信息
     * @return 影响条数
     */
    int insert(AddressBookDO addressBookDO);

    /**
     * 根据id查询地址信息
     * @param id 地址信息id
     * @return 地址信息
     */
    AddressBookDO getById(@Param("id") Long id);

    /**
     * 根据id修改地址信息
     * @param addressBookDO 地址信息
     * @return 影响条数
     */
    int update(AddressBookDO addressBookDO);

    /**
     * 根据用户id修改 是否默认地址
     * @param isDefault 是否默认地址， 1：是，0：否
     * @param userId 用户id
     * @return 影响条数
     */
    int updateIsDefaultByUserId(@Param("isDefault") Integer isDefault, @Param("userId") String userId);

    /**
     * 根据id删除地址
     * @param id 地址信息id
     */
    int deleteById(@Param("id") Long id);

}
