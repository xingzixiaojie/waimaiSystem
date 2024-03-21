package com.chen.api.goods.controller;

import cn.hutool.core.bean.BeanUtil;
import com.chen.api.goods.po.DishPO;
import com.chen.common.result.Result;
import com.chen.core.goods.bo.DishBO;
import com.chen.core.goods.entity.DishDO;
import com.chen.core.goods.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "4.1 菜品相关接口")
@RequestMapping("/admin/dish")
@RestController
public class DishController {

    @Resource
    private DishService dishService;

    @ApiOperation("4.1.1 新增菜品")
    @PostMapping
    public Result create(@RequestBody DishPO po){
        DishDO byName = dishService.getByName(po.getName());
        if(byName != null){
            return Result.error("菜品名称已存在");
        }

        DishBO dishBO = new DishBO();
        BeanUtil.copyProperties(po, dishBO);
        dishBO.setFlavorList(po.getFlavors());
        boolean flag = dishService.insertDish(dishBO);
        if(flag){
            return Result.success();
        }else {
            return Result.error("操作失败");
        }

    }

}
