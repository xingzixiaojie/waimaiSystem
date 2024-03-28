package com.chen.core.address.service.impl;

import com.chen.common.context.BaseContext;
import com.chen.core.address.entity.AddressBookDO;
import com.chen.core.address.mapper.AddressBookMapper;
import com.chen.core.address.service.AddressBookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/** C端用户的收货地址信息 */
@Service
public class AddressBookServiceImpl implements AddressBookService {

    /** C端用户的收货地址信息 */
    @Resource
    private AddressBookMapper addressBookMapper;

    /**
     * 条件查询所有地址信息
     * @param userId 用户id，查询所有填NULL
     * @param phone 手机号，查询所有填NULL
     * @param isDefault 是否默认地址， 1：是，0：否 ，查询所有填NULL
     * @return
     */
    @Override
    public List<AddressBookDO> list(String userId, String phone, Integer isDefault){
        return addressBookMapper.list(userId, phone, isDefault);
    }

    /**
     * 新增地址信息
     * @param addressBookDO 地址信息
     * @return 操作成功:返回true，操作失败:返回false
     */
    @Override
    public boolean insert(AddressBookDO addressBookDO){
        addressBookDO.setUserId(BaseContext.getCurrentId().toString());
        addressBookDO.setIsDefault(0);
        return addressBookMapper.insert(addressBookDO) > 0;
    }

    /**
     * 根据id查询地址信息
     * @param id 地址信息id
     * @return 地址信息
     */
    @Override
    public AddressBookDO getById(Long id){
        return addressBookMapper.getById(id);
    }

    /**
     * 根据id修改地址信息
     * @param addressBookDO 地址信息
     * @return 操作成功:返回true，操作失败:返回false
     */
    @Override
    public boolean update(AddressBookDO addressBookDO){
        return addressBookMapper.update(addressBookDO) > 0;
    }

    /**
     * 根据用户id修改默认地址
     * @Param addressBookDO 地址信息
     * @return 操作成功:返回true，操作失败:返回false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean setDefault(AddressBookDO addressBookDO){
        //1、将当前用户的所有地址修改为非默认地址
        addressBookDO.setIsDefault(0);
        addressBookDO.setUserId(BaseContext.getCurrentId().toString());
        boolean executeSuccess = addressBookMapper.updateIsDefaultByUserId(addressBookDO.getIsDefault(),
                                            BaseContext.getCurrentId().toString()) > 0;

        //2、将当前地址改为默认地址
        if(executeSuccess){
            addressBookDO.setIsDefault(1);
            executeSuccess = this.update(addressBookDO);
        }
        if (!executeSuccess){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    /**
     * 根据id删除地址
     * @param id 地址信息id
     * @return 操作成功:返回true，操作失败:返回false
     */
    @Override
    public boolean deleteById(Long id){
        return addressBookMapper.deleteById(id) > 0;
    }

}
