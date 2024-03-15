package com.chen.common.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 查询页码对象
 */
@Data
public class QueryPage implements Serializable {

    /** 查询页码 */
    private Integer pageNum = 1;

    /** 每页行数 */
    private Integer pageSize = 20;


    /**
     * 分页对象
     * @param pageNum 查询页码，页码从1开始
     * @param pageSize 每页行数，默认20，最大1000
     * @return 分页对象
     */
    public QueryPage(Integer pageNum, Integer pageSize) {
        if(pageNum != null && pageNum.intValue() > 0 && pageNum.intValue() < Integer.MAX_VALUE){
            this.pageNum = pageNum;
        }

        if(pageSize != null && pageSize.intValue() > 0 && pageSize.intValue() <= 1000){
            this.pageSize = pageSize;
        }
    }

}
