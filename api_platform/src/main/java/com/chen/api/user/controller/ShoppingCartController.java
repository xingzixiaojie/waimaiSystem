package com.chen.api.user.controller;

import cn.hutool.core.bean.BeanUtil;
import com.chen.api.user.po.ShoppingCartPO;
import com.chen.common.context.BaseContext;
import com.chen.common.result.Result;
import com.chen.core.order.entity.ShoppingCartDO;
import com.chen.core.order.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "10.6 用户端购物车接口")
@RequestMapping("/user/shoppingCart")
public class ShoppingCartController {

    /** 用户端购物车 */
    @Resource
    private ShoppingCartService shoppingCartService;

    @ApiOperation("10.6.1 添加购物车")
    @PostMapping("/add")
    public Result add(@RequestBody ShoppingCartPO po){
        ShoppingCartDO shoppingCartDO = new ShoppingCartDO();
        BeanUtil.copyProperties(po, shoppingCartDO);
        boolean flag = shoppingCartService.addShoppingCart(shoppingCartDO);
        if (flag){
            return Result.success();
        }else{
            return Result.error("操作失败");
        }
    }

    @ApiOperation(("10.6.2 查看购物车"))
    @GetMapping("/list")
    public Result<List<ShoppingCartDO>> list(){
        List<ShoppingCartDO> shoppingCartDOList = shoppingCartService.list(BaseContext.getCurrentId(), null,
                                                                null, null);
        return Result.success(shoppingCartDOList);
    }

    @ApiOperation(("10.6.3 清空购物车"))
    @DeleteMapping("/clean")
    public Result clean(){
        boolean flag = shoppingCartService.deleteByUserId(BaseContext.getCurrentId());
        if (flag){
            return Result.success();
        }else{
            return Result.error("操作失败");
        }
    }

    @ApiOperation(("10.6.4 删除购物车中一个商品"))
    @PostMapping("/sub")
    public Result sub(@RequestBody ShoppingCartPO po){
        ShoppingCartDO shoppingCartDO = new ShoppingCartDO();
        BeanUtil.copyProperties(po, shoppingCartDO);
        boolean flag = shoppingCartService.subShoppingNumber(shoppingCartDO);
        if (flag){
            return Result.success();
        }else{
            return Result.error("操作失败");
        }
    }
}
