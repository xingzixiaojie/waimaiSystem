package com.chen.common.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 基础controller
 */
public class BaseController {

    /** 请求对象 */
    @Resource
    protected HttpServletRequest request;

}
