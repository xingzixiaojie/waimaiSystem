package com.chen.api.goods.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/** 封装分页查询结果 */
@ApiModel(value = "封装分页查询结果")
@Data
public class CategoryPageResult implements Serializable {

    /** 总记录条数 */
    @ApiModelProperty(value = "总记录条数", required = true)
    private long total;

    /** 当前页数据集合 */
    @ApiModelProperty(value = "当前页数据集合", required = true)
    private List records;

}
