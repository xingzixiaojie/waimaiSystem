package com.chen.common.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 业务返回结构
 */
@Data
public class ResultBO<T> implements Serializable {

    /** 业务状态码 */
    @ApiModelProperty(required = true, value ="业务状态码")
    private Integer code;

    /** 业务说明 */
    @ApiModelProperty(required = true, value ="业务说明")
    private String message;

    /** 业务数据 */
    @ApiModelProperty(value ="业务说明")
    private T data;

    public ResultBO<T> code(Integer code) {
        this.code = code;
        return this;
    }

    public ResultBO<T> message(String message) {
        this.message = message;
        return this;
    }

    public ResultBO<T> data(T data) {
        this.data = data;
        return this;
    }


    @Override
    public String toString() {
        return "Result{code=" + code + ",message='" + message + "'," + "data=" + data +"}";
    }

}