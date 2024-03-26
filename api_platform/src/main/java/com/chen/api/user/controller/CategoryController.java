package com.chen.api.user.controller;

import com.chen.common.result.Result;
import com.chen.core.goods.entity.CategoryDO;
import com.chen.core.goods.service.CategoryService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController("userCategoryController")
@RequestMapping("/user/category")
@Api(tags = "10.3 C端-分类接口")
public class CategoryController {

    /** 商品的分类信息 */
    @Resource
    private CategoryService categoryService;


    @GetMapping("/list")
    @ApiOperation("10.3.1 查询分类")
    public Result<List<CategoryDO>> list(Integer type) {
        PageInfo<CategoryDO> pageInfo = categoryService.pageQuery(null, null, type, null);
        return Result.success(pageInfo.getList());
    }

}
