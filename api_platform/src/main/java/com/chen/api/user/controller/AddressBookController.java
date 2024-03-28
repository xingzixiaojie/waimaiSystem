package com.chen.api.user.controller;


import cn.hutool.core.collection.CollUtil;
import com.chen.common.context.BaseContext;
import com.chen.common.result.Result;
import com.chen.core.address.entity.AddressBookDO;
import com.chen.core.address.service.AddressBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user/addressBook")
@Api(tags = "10.7 C端地址簿接口")
public class AddressBookController {

    /** C端用户的收货地址信息 */
    @Resource
    private AddressBookService addressBookService;

    @GetMapping("/list")
    @ApiOperation("10.7.1 查询当前登录用户的所有地址信息")
    public Result<List<AddressBookDO>> list() {
        List<AddressBookDO> list = addressBookService.list(BaseContext.getCurrentId().toString(), null, null);
        return Result.success(list);
    }

    @PostMapping
    @ApiOperation("10.7.2 新增地址")
    public Result save(@RequestBody AddressBookDO addressBook) {
        boolean flag = addressBookService.insert(addressBook);
        if (flag){
            return Result.success();
        }else{
            return Result.error("操作失败");
        }
    }

    @GetMapping("/{id}")
    @ApiOperation("10.7.3 根据id查询地址")
    public Result<AddressBookDO> getById(@PathVariable Long id) {
        AddressBookDO addressBook = addressBookService.getById(id);
        return Result.success(addressBook);
    }

    @PutMapping
    @ApiOperation("10.7.4 根据id修改地址")
    public Result update(@RequestBody AddressBookDO addressBook) {
        boolean flag = addressBookService.update(addressBook);
        if (flag){
            return Result.success();
        }else{
            return Result.error("操作失败");
        }
    }

    @PutMapping("/default")
    @ApiOperation("设置默认地址")
    public Result setDefault(@RequestBody AddressBookDO addressBook) {
        boolean flag = addressBookService.setDefault(addressBook);
        if (flag){
            return Result.success();
        }else{
            return Result.error("操作失败");
        }
    }

    @DeleteMapping
    @ApiOperation("根据id删除地址")
    public Result deleteById(Long id) {
        boolean flag = addressBookService.deleteById(id);
        if (flag){
            return Result.success();
        }else{
            return Result.error("操作失败");
        }
    }

    @GetMapping("default")
    @ApiOperation("查询默认地址")
    public Result<AddressBookDO> getDefault() {
        List<AddressBookDO> list = addressBookService.list(BaseContext.getCurrentId().toString(), null, 1);
        if(CollUtil.isNotEmpty(list)){
            return Result.success(list.get(0));
        }
        return Result.error("没有查询到默认地址");
    }

}
