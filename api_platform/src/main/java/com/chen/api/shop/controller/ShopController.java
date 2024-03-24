package com.chen.api.shop.controller;

import com.chen.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "5.1 店铺相关接口")
@RestController(value = "AdminShopController")
@RequestMapping("/admin/shop")
public class ShopController {

    public static final String KEY = "SHOP_STATUS";

    /** Redis */
    @Resource
    private RedisTemplate redisTemplate;

    @ApiOperation("5.1.1 后台设置店铺营业状态")
    @PutMapping("/{status}")
    public Result setStatus(@PathVariable Integer status){
        redisTemplate.opsForValue().set(KEY, status);
        Boolean flag = redisTemplate.hasKey(KEY);
        if(flag.booleanValue()){
            return Result.success();
        }else {
            return Result.error("操作失败");
        }
    }

    @ApiOperation("5.1.2 后台获取店铺的营业状态")
    @GetMapping("/status")
    public Result<Integer> getStatus(){
        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        if(status == null){
            return Result.error("获取店铺的营业状态失败");
        }else {
            return Result.success(status);
        }
    }

}
