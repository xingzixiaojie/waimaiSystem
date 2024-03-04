package com.chen.common.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Redis操作工具类
 */
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 通过键删除一个值
     * @param key 键的名称
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 判断key是否存在
     * @param key 键的名称
     * @return 存在：true，不存在：false
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 数据存储
     * @param key 键
     * @param value 值
     * @param expireTime 过期时间，单位：秒
     */
    public void set(String key, Object value, Long expireTime) {
        redisTemplate.boundValueOps(key).set(value, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 数据取值
     * @param key 键
     * @return 查询成功：值，查询失败，null
     */
    public Object get(String key) {
        return redisTemplate.boundValueOps(key).get();
    }

    /**
     * 设置键的过期时间
     * @param key 键
     * @param expireTime 过期时间，单位：秒
     */
    public void expire(String key, Long expireTime){
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

}
