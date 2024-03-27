package com.chen.api.user.controller;


import com.chen.api.user.vo.DishItemVO;
import com.chen.common.result.Result;
import com.chen.core.goods.entity.DishDO;
import com.chen.core.goods.entity.SetmealDO;
import com.chen.core.goods.entity.SetmealDishDO;
import com.chen.core.goods.service.DishService;
import com.chen.core.goods.service.SetmealDishService;
import com.chen.core.goods.service.SetmealService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("userSetmealController")
@RequestMapping("/user/setmeal")
@Api(tags = "10.5 C端-套餐浏览接口")
public class SetmealController {

    /** 套餐和菜品的关联关系 */
    @Resource
    private SetmealDishService setmealDishService;

    /** 套餐的信息 */
    @Resource
    private SetmealService setmealService;

    /** 菜品的信息 */
    @Resource
    private DishService dishService;

    /**
     * 条件查询
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("10.5.1 根据分类id查询套餐")
    @Cacheable(cacheNames = "setmealCache", key = "#categoryId")
    public Result<List<SetmealDO>> list(Long categoryId) {
        PageInfo<SetmealDO> setmealDOPageInfo = setmealService.list(null, categoryId, null, 1);
        return Result.success(setmealDOPageInfo.getList());
    }

    /**
     * 根据套餐id查询包含的菜品列表
     *
     * @param id
     * @return
     */
    @GetMapping("/dish/{id}")
    @ApiOperation("10.5.2 根据套餐id查询包含的菜品列表")
    public Result<List<DishItemVO>> dishList(@PathVariable("id") Long id) {
        List<SetmealDishDO> setmealDishDOList = setmealDishService.listBySetmealId(id);
        List<DishItemVO> resultList = new ArrayList<>();
        List<Long> dishIds = new ArrayList<>();
        setmealDishDOList.forEach(setmealDishDO -> dishIds.add(setmealDishDO.getDishId()));
        List<DishDO> dishDOList = dishService.listByIds(dishIds);
        Map<Long, DishItemVO> dishItemVOMap = new HashMap<>();
        for(DishDO dishDO : dishDOList){
            DishItemVO dishItemVO = new DishItemVO();
            dishItemVOMap.put(dishDO.getId(), dishItemVO);
        }
        for(SetmealDishDO setmealDishDO : setmealDishDOList){
            DishItemVO dishItemVO = dishItemVOMap.get(setmealDishDO.getDishId());
            dishItemVO.setCopies(setmealDishDO.getCopies());
        }

        for(DishDO dishDO : dishDOList){
            DishItemVO dishItemVO = dishItemVOMap.get(dishDO.getId());
            dishItemVO.setName(dishDO.getName());
            dishItemVO.setDescription(dishDO.getDescription());
            dishItemVO.setImage(dishDO.getImage());
            resultList.add(dishItemVO);
        }

        return Result.success(resultList);
    }

}
