package com.chen.api.goods.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.chen.api.goods.po.DishPO;
import com.chen.api.goods.po.DishPageQueryPO;
import com.chen.api.goods.vo.DishVO;
import com.chen.common.result.PageResult;
import com.chen.common.result.QueryPage;
import com.chen.common.result.Result;
import com.chen.common.utils.DateUtil;
import com.chen.core.goods.bo.DishBO;
import com.chen.core.goods.entity.CategoryDO;
import com.chen.core.goods.entity.DishDO;
import com.chen.core.goods.entity.DishFlavorDO;
import com.chen.core.goods.service.CategoryService;
import com.chen.core.goods.service.DishFlavorService;
import com.chen.core.goods.service.DishService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "4.1 菜品相关接口")
@RequestMapping("/admin/dish")
@RestController
public class DishController {

    /** 菜品的信息 */
    @Resource
    private DishService dishService;

    /** 商品的分类信息 */
    @Resource
    private CategoryService categoryService;

    /** 菜品的口味信息 */
    @Resource
    private DishFlavorService dishFlavorService;

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

    @ApiOperation("4.1.2 菜品分页查询")
    @GetMapping("/page")
    public Result<PageResult> page(DishPageQueryPO po){
        PageInfo<DishDO> dishDOPageInfo = dishService.list(new QueryPage(po.getPage(), po.getPageSize()), po.getName(), po.getCategoryId(), po.getStatus());
        if(CollUtil.isEmpty(dishDOPageInfo.getList())){
            return Result.success(new PageResult(dishDOPageInfo.getTotal(), dishDOPageInfo.getList()));
        }

        List<DishDO> dishDOList = dishDOPageInfo.getList();
        List<DishVO> resultList = new ArrayList<>();
        for(DishDO dishDO : dishDOList){
            DishVO dishVO = new DishVO();
            BeanUtil.copyProperties(dishDO, dishVO);
            String timeToStr = DateUtil.timeToStr(dishDO.getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(timeToStr, dateTimeFormatter);
            dishVO.setUpdateTime(localDateTime);
            resultList.add(dishVO);
        }

        List<CategoryDO> categoryDOList = categoryService.listAll();
        for(DishVO dishVO : resultList){
            for(CategoryDO categoryDO : categoryDOList){
                if(dishVO.getCategoryId().longValue() == categoryDO.getId().longValue()){
                    dishVO.setCategoryName(categoryDO.getName());
                }
            }
        }

        List<DishFlavorDO> dishFlavorDOList = dishFlavorService.listAll();
        for(DishVO dishVO : resultList){
            List<DishFlavorDO> flavorDOList = new ArrayList<>();
            for(DishFlavorDO dishFlavorDO : dishFlavorDOList){
                if(dishVO.getId().longValue() == dishFlavorDO.getDishId().longValue()){
                    flavorDOList.add(dishFlavorDO);
                }
            }
            dishVO.setFlavors(flavorDOList);
        }

        return Result.success(new PageResult(dishDOPageInfo.getTotal(), resultList));
    }

}