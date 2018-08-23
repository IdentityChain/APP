package com.project.isc.iscdbserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by on 2017/3/1.
 */
@Service
public class RedisService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valOpsStr;



    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    ValueOperations<String, Object> valOpsObj;

    /**
     * 根据指定key获取String
     * @param key
     * @return
     */
    public String getStr(String key){
        return valOpsStr.get(key);
    }

    /**
     * 设置Str缓存
     * @param key
     * @param val
     */
    public void setStr(String key, String val){
        valOpsStr.set(key,val);
    }

    /**
     * 删除指定key
     * @param key
     */
    public void del(String key){
        stringRedisTemplate.delete(key);
    }

    /**
     * 根据指定o获取Object
     * @param key
     * @return
     */
    public Object getObj(String key){
        return valOpsObj.get(key);
    }

    /**
     * 根据指定key查看是否存在
     * @param  key
     */
    public boolean ExistsKey(String key) { return  redisTemplate.hasKey(key);}


    /**
     * 根据指定key 查看剩余存活时间
     * @param key
     * @return
     */
    public Long getFreeTime(String key) { return  redisTemplate.getExpire(key);}

    /**
     * 设置obj缓存
     * @param key
     * @param o2
     */
    public void setObj(String key, Object o2, long expireSecond){
        valOpsObj.set(key, o2, expireSecond, TimeUnit.SECONDS);
    }

    /**
     * 设置Key的过期时间,单位:秒
     * @param key
     * @param expireSecond
     */
    public void setKeyExpireSecond(String key, long expireSecond){
        redisTemplate.expire(key, expireSecond, TimeUnit.SECONDS);
    }

    /**
     * 删除Obj缓存
     * @param o
     */
    public void delObj(String o){
        redisTemplate.delete(o);
    }

    /**
     * 从左边插入对象至List
     * @param key
     * @param obj 要存放的对象
     */
    public void pushObjToList(String key,Object obj) {
        redisTemplate.opsForList().leftPush(key, obj);
    }

    /**
     * 从指定List左边获取对象
     * @param key
     * @return 获取的对象
     */
    public Object getObjFromList(String key) {
        return  redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 查询指定列表的元素数量
     * @param key
     * @return 元素数量
     */
    public Long getListSize(String key) {
        return redisTemplate.opsForList().size(key);
    }
}
