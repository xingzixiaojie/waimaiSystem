package com.chen.core.goods.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/** 商品的分类信息 */
@Data
public class CategoryDO implements Serializable {

    /** 主键，自增 */
    private Long id;

    /** 分类类型, 1：菜品分类，2：套餐分类 */
    private Integer type;

    /** 分类名称  */
    private String name;

    /** 排序字段, 用于分类数据的排序 */
    private Integer sort;

    /** 状态, 1：启用，0：禁用 */
    private Integer status;

    /** 菜品创建时间 */
    private Timestamp createTime;

    /** 菜品最后修改时间 */
    private Timestamp updateTime;

    /** 菜品创建人id */
    private Long createUser;

    /** 菜品最后修改人id */
    private Long updateUser;

}
