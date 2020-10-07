package com.iflytek.config.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author AZhao
 */
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @param key  键
     * @param time 时间(秒)
     * @return
     * @describe 指定缓存失效时间
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     * @describe 根据key 获取过期时间
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * @param key
     * @return
     * @describe 自增1
     */
    public long incr(String key) {
        return redisTemplate.opsForValue().increment(key, 1);
    }

    /**
     * @param key
     * @return
     * @describe 减少1
     */
    public long deincr(String key) {
        return redisTemplate.opsForValue().increment(key, -1);
    }

    /**
     * 正则匹配key
     *
     * @param pattern key
     * @return 返回key set
     */
    public Set<String> keys(String pattern) {
        return pattern == null ? null : redisTemplate.keys(pattern);
    }

    /**
     * @param key 键
     * @return true 存在 false不存在
     * @describe 判断key是否存在
     */

    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param key 可以传一个值 或多个
     * @return
     * @describe 删除缓存
     */

    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * @param key set 列表
     * @return
     * @describe 删除缓存
     */
    public void del(Set<String> key) {
        if (key != null && key.size() > 0) {
            redisTemplate.delete(key);
        }
    }

    /**
     * @param key 键
     * @return 值
     * @describe 普通缓存获取
     */

    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * @param key 键
     * @return 值
     * @describe 普通缓存获取
     */

    public Object get(Object key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     * @describe 普通缓存放入
     */

    public int set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    /**
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     * @describe 普通缓存放入并设置时间
     */

    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param key 键
     *            要增加几(大于0)
     * @return 返回更新后的值
     * @describe 递增
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        if (!hasKey(key)) {
            set(key, delta);
            return delta;
        }
        long count = Long.parseLong(String.valueOf(get(key))) + delta;
        set(key, count);
        return count;
    }


    /**
     * @param key 键 要减少几(小于0)
     * @return
     * @describe 递减
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        if (!hasKey(key)) {
            set(key, delta);
            return delta;
        }
        long count = Long.parseLong(String.valueOf(get(key))) - delta;
        set(key, count);
        return count;
    }

    // ================================Map=================================


    /**
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     * @describe HashGet
     */
    public Object hget(String key, Object item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * @param key 键
     * @return
     * @describe 获取hashKey对应的所有键值
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     * @describe HashSet
     */

    public boolean hmset(String key, Map<Object, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     * @describe HashSet 并设置时间
     */
    public boolean hmset(String key, Map<Object, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     * @describe 向一张hash表中放入数据, 如果不存在将创建
     */
    public boolean hset(String key, Object item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     * @describe 向一张hash表中放入数据, 如果不存在将创建
     */
    public boolean hset(String key, Object item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     * @return
     * @describe 删除hash表中的值
     */

    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     * @describe 判断hash表中是否有该项的值
     */
    public boolean hHasKey(String key, Object item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     * @describe hash递增 如果不存在,就会创建一个 并把新增后的值返回
     */
    public double hincr(String key, Object item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     * @describe hash递减
     */

    public double hdecr(String key, Object item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    //============================set=============================

    /**
     * @param key 键
     * @return
     * @describe 根据key获取Set中的所有值
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     * @describe 根据value从一个set中查询, 是否存在
     */

    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     * @describe 将数据放入set缓存
     */
    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     * @describe 将set数据放入缓存
     */
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0)
                expire(key, time);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @param key 键
     * @return
     * @describe 获取set缓存的长度
     */

    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     * @describe 移除值为value的
     */

    public long setRemove(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().remove(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    //===============================list=================================

    /**
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @return
     * @describe 获取list缓存的内容
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param key 键
     * @return
     * @describe 获取list缓存的长度
     */
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     * @describe 通过索引 获取list中的值
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param key   键
     * @param value 值
     * @return
     * @describe 将list放入缓存
     */
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     * @describe 将list放入缓存
     */

    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0)
                expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * @param key   键
     * @param value 值
     * @return
     * @describe 将list放入缓存
     */
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     * @describe 将list放入缓存
     */

    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     * @describe 根据索引修改list中的某条数据
     */

    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param key   键
     * @param count 移除多少个
     * @return value 值
     * @describe 移除N个值为value
     */
    public long lRemove(String key, long count, Object value) {
        try {
            return redisTemplate.opsForList().remove(key, count, value);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @param key
     * @param start
     * @param end
     * @describe 截取key 的List ，保留长度内的数据。
     */
    public void lTrim(String key, long start, long end) {
        try {
            redisTemplate.opsForList().trim(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param key
     * @describe 清空key 的List
     */
    public void clear(String key) {
        lTrim(key, -1, 0);
    }


    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
