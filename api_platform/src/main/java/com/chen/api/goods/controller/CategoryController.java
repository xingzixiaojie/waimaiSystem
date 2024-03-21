package com.chen.api.goods.controller;


import cn.hutool.core.bean.BeanUtil;
import com.chen.api.goods.po.CategoryCreatePO;
import com.chen.api.goods.po.CategoryPageQueryPO;
import com.chen.common.result.PageResult;
import com.chen.common.result.QueryPage;
import com.chen.common.result.Result;
import com.chen.core.goods.entity.CategoryDO;
import com.chen.core.goods.service.CategoryService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分类管理
 */
@RestController
@RequestMapping("/admin/category")
@Api(tags = "2.1 分类管理")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @PostMapping
    @ApiOperation("2.1.1 新增分类")
    public Result<String> save(@RequestBody CategoryCreatePO po){
        CategoryDO categoryDO = new CategoryDO();
        BeanUtil.copyProperties(po,categoryDO);
        boolean flag = categoryService.save(categoryDO);
        if(flag){
            return Result.success();
        }
        return Result.error("新增失败");
    }

    @GetMapping("/page")
    @ApiOperation("2.1.2 分类分页查询")
    public Result<PageResult> page(CategoryPageQueryPO po){
        PageInfo<CategoryDO> categoryDOPageInfo = categoryService.pageQuery(new QueryPage(po.getPage(), po.getPageSize()),
                                                                        po.getName(), po.getType(), null);
        PageResult pageResult = new PageResult();
        pageResult.setTotal(categoryDOPageInfo.getTotal());
        pageResult.setRecords(categoryDOPageInfo.getList());
        return Result.success(pageResult);
    }

    @DeleteMapping
    @ApiOperation("2.1.3 删除分类")
    public Result<String> deleteById(Long id){
        boolean flag = categoryService.deleteById(id);
        if(flag){
            return Result.success();
        }

        return Result.error("删除失败");
    }

    @PutMapping
    @ApiOperation("2.1.4 修改分类")
    public Result<String> update(@RequestBody CategoryCreatePO po){
        CategoryDO categoryDO = new CategoryDO();
        BeanUtil.copyProperties(po, categoryDO);
        boolean flag = categoryService.update(categoryDO);
        if(flag){
            return Result.success();
        }
        return Result.error("修改失败");
    }

    @PostMapping("/status/{status}")
    @ApiOperation("2.1.5 启用禁用分类")
    public Result<String> startOrStop(@PathVariable("status") Integer status, Long id){
        boolean flag = categoryService.startOrStop(status, id);
        if(flag){
            return Result.success();
        }
        return Result.error("启用禁用失败");
    }

    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("2.1.6 根据类型查询分类")
    public Result<List<CategoryDO>> list(Integer type){
        PageInfo<CategoryDO> categoryDOPageInfo = categoryService.pageQuery(null, null, type, null);
        List<CategoryDO> returnList = categoryDOPageInfo.getList();
        return Result.success(returnList);
    }

}
