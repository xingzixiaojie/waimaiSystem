package com.chen.api.goods.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.chen.api.goods.po.SetmealPO;
import com.chen.api.goods.po.SetmealPageQueryPO;
import com.chen.api.goods.vo.SetmealPageResultVO;
import com.chen.api.goods.vo.SetmealVO;
import com.chen.common.result.QueryPage;
import com.chen.common.result.Result;
import com.chen.common.utils.DateUtil;
import com.chen.core.goods.bo.SetmealBO;
import com.chen.core.goods.entity.CategoryDO;
import com.chen.core.goods.entity.SetmealDO;
import com.chen.core.goods.entity.SetmealDishDO;
import com.chen.core.goods.service.CategoryService;
import com.chen.core.goods.service.SetmealDishService;
import com.chen.core.goods.service.SetmealService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "6.1 套餐相关接口")
@RestController
@RequestMapping("/admin/setmeal")
public class SetmealController {

    /** 套餐和菜品的关联关系 */
    @Resource
    private SetmealDishService setmealDishService;

    /** 套餐的信息 */
    @Resource
    private SetmealService setmealService;

    /** 商品的分类信息 */
    @Resource
    private CategoryService categoryService;

    @ApiOperation("6.1.1 根据id查询套餐")
    @GetMapping("/{id}")
    public Result<SetmealVO> getById(@PathVariable Long id){
        SetmealDO setmealDO = setmealService.getById(id);
        SetmealVO setmealVO = new SetmealVO();
        if(setmealDO != null){
            BeanUtil.copyProperties(setmealDO, setmealVO);
            List<SetmealDishDO> setmealDishDOList = setmealDishService.listBySetmealId(id);
            List<CategoryDO> categoryDOList = categoryService.listAll();
            for(CategoryDO categoryDO : categoryDOList){
                if(setmealDO.getCategoryId().longValue() == categoryDO.getId()){
                    setmealVO.setCategoryName(categoryDO.getName());
                }
            }
            setmealVO.setUpdateTime(DateUtil.timeToStr(setmealDO.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
            setmealVO.setSetmealDishes(setmealDishDOList);
        }
        return Result.success(setmealVO);
    }

    @ApiOperation("6.1.2 新增套餐")
    @PostMapping
    @CacheEvict(cacheNames = "setmealCache", key = "#po.categoryId")
    public Result create(@RequestBody SetmealPO po){
        SetmealBO setmealBO = new SetmealBO();
        BeanUtil.copyProperties(po, setmealBO);
        boolean flag = setmealService.insert(setmealBO);
        if(flag){
            return Result.success();
        }else {
            return Result.error("操作失败");
        }
    }

    @ApiOperation("6.1.3 修改套餐")
    @PutMapping
    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    public Result update(@RequestBody SetmealPO po){
        SetmealBO setmealBO = new SetmealBO();
        BeanUtil.copyProperties(po, setmealBO);
        boolean flag = setmealService.update(setmealBO);
        if(flag){
            return Result.success();
        }else {
            return Result.error("操作失败");
        }
    }

    @ApiOperation("6.1.4 分页查询")
    @GetMapping("/page")
    public Result<SetmealPageResultVO> page(SetmealPageQueryPO po){
        PageInfo<SetmealDO> setmealDOPageInfo = setmealService.list(new QueryPage(po.getPage(), po.getPageSize()),
                po.getCategoryId(), po.getName(), po.getStatus());
        if(CollUtil.isEmpty(setmealDOPageInfo.getList()) || setmealDOPageInfo == null){
            return  Result.success(new SetmealPageResultVO(setmealDOPageInfo.getTotal(), setmealDOPageInfo.getList()));
        }

        List<SetmealVO> resultList = new ArrayList<>();
        for(SetmealDO setmealDO : setmealDOPageInfo.getList()){
            SetmealVO setmealVO = new SetmealVO();
            BeanUtil.copyProperties(setmealDO, setmealVO);
            List<SetmealDishDO> setmealDishDOList = setmealDishService.listBySetmealId(setmealDO.getId());
            List<CategoryDO> categoryDOList = categoryService.listAll();
            for(CategoryDO categoryDO : categoryDOList){
                if(setmealDO.getCategoryId().longValue() == categoryDO.getId()){
                    setmealVO.setCategoryName(categoryDO.getName());
                }
            }
            setmealVO.setUpdateTime(DateUtil.timeToStr(setmealDO.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
            setmealVO.setSetmealDishes(setmealDishDOList);
            resultList.add(setmealVO);
        }
        return Result.success(new SetmealPageResultVO(setmealDOPageInfo.getTotal(), setmealDOPageInfo.getList()));
    }

    @ApiOperation("6.1.5 批量删除套餐")
    @DeleteMapping
    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    public Result deleteBatch(@RequestParam List<Long> ids){
        boolean flag = setmealService.deleteBatchByIds(ids);
        if(flag){
            return Result.success();
        }else {
            return Result.error("操作失败");
        }
    }

    @ApiOperation("6.1.6 套餐起售、停售")
    @PostMapping("/status/{status}")
    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    public Result updateStatus(@PathVariable("status") Integer status, Long id){
        boolean flag = setmealService.updateStatus(id, status);
        if(flag){
            return Result.success();
        }else {
            return Result.error("操作失败");
        }
    }

}
