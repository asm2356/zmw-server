package com.iflytek.config.service.shiro;

import com.iflytek.common.constant.RedisPrefix;
import com.iflytek.config.service.app.SystemConfigUtils;
import com.iflytek.config.service.redis.RedisService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author zgzhao
 * Shiro缓存实现
 */
public class ShiroRedisCache<K, V> implements Cache<K, V> {
    private long expireTime = Long.parseLong(SystemConfigUtils.getValue("shiro.expireTime"));// 缓存的超时时间，单位为s


    @Autowired
    private RedisService redisService;
    /**
     * @param o key
     * @return value
     * @author zgzhao
     * @describe
     */
    @Override
    public Object get(Object o) throws CacheException {
        if (o == null) {
            return null;
        }
        return redisService.get(RedisPrefix.SHIRO_CACHE + o);
    }

    /**
     * @param o  key
     * @param o2 value
     * @return value
     * @author zgzhao
     * @describe set
     */
    @Override
    public Object put(Object o, Object o2) throws CacheException {
        if (o == null || o2 == null) {
            return null;
        }
        redisService.set(RedisPrefix.SHIRO_CACHE + o, o2, expireTime);
        return o2;
    }

    /**
     * @param o key
     * @return value
     * @throws CacheException
     */
    @Override
    public Object remove(Object o) throws CacheException {
        if (o == null) {
            return null;
        }
        //Object job = RedisService.get(RedisPrefix.SHIRO_CACHE + o);
        //RedisService.getRedisTemplate().delete(job);
        return o;
    }

    @Override
    public void clear() throws CacheException {
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set keys() {
        return redisService.keys(RedisPrefix.SHIRO_CACHE + "*");
    }

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 获取values 集合
     */
    @Override
    public Collection values() {
        Set keys = this.keys();
        List<Object> values = new ArrayList<>();
        for (Object key : keys) {
            Object value = redisService.get(key);
            values.add(value);
        }
        return values;
    }
}
