package com.chen.api.employee.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/** 查询员工PO */
@ApiModel("查询员工PO")
@Data
public class EmployeeQueryPO implements Serializable {

    /** 员工姓名 */
    @ApiModelProperty(value = "员工姓名", required = false)
    private String name;

    /** 页码 */
    @ApiModelProperty(value = "页码", required = true, example = "1")
    private int page;

    /** 每页显示记录条数 */
    @ApiModelProperty(value = "每页显示记录条数", required = true, example = "10")
    private int pageSize;

}
