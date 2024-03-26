package com.chen.api.user.controller;


import cn.hutool.core.bean.BeanUtil;
import com.chen.api.goods.vo.DishVO;
import com.chen.common.result.Result;
import com.chen.common.utils.DateUtil;
import com.chen.core.goods.entity.CategoryDO;
import com.chen.core.goods.entity.DishDO;
import com.chen.core.goods.entity.DishFlavorDO;
import com.chen.core.goods.service.CategoryService;
import com.chen.core.goods.service.DishFlavorService;
import com.chen.core.goods.service.DishService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController("userDishController")
@RequestMapping("/user/dish")
@Api(tags = "10.4 C端-菜品浏览接口")
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


    /**
     * 根据分类id查询菜品
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("10.4.1 根据分类id查询菜品")
    public Result<List<DishVO>> list(Long categoryId) {

        PageInfo<DishDO> dishDOPageInfo = dishService.list(null, null, categoryId, 1);
        List<DishDO> dishDOList = dishDOPageInfo.getList();
        List<DishVO> resultList = new ArrayList<>();
        for(DishDO dishDO : dishDOList){
            DishVO dishVO = new DishVO();
            BeanUtil.copyProperties(dishDO, dishVO);
            dishVO.setUpdateTime(DateUtil.timeToStr(dishDO.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
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

        return Result.success(resultList);
    }

}
