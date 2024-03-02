package com.chen.common.result;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页列表数据
 */
@ApiModel("列表数据")
@Data
public class ResultPageInfo<T> implements Serializable {

    /** 当前页码 */
    @ApiModelProperty("当前页码")
    private Integer pageNum;

    /** 每页的数量 */
    @ApiModelProperty("每页的数量")
    private Integer pageSize;

    /** 总记录数 */
    @ApiModelProperty("总记录数")
    private Long total;

    /** 总页数 */
    @ApiModelProperty("总页数")
    private Integer pages;

    /** 列表数据 */
    @ApiModelProperty("列表数据")
    private List<T> list;

    /**
     * 创建分页信息
     * @param pageInfo 原分页信息
     */
    public ResultPageInfo(PageInfo pageInfo) {
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.pages = pageInfo.getPages();
        this.total = pageInfo.getTotal();
    }

    /**
     * 创建分页信息
     * @param pageInfo 原分页信息
     * @param list 数据列表
     */
    public ResultPageInfo(PageInfo pageInfo, List<T> list) {
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.pages = pageInfo.getPages();
        this.total = pageInfo.getTotal();
        this.list = list;
    }

}
