package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @description: Redis 帮 助 类组 件
 * @author: zm
 * @create: 2022-06-25 15:39
 */
@Component
@Slf4j
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public RedisUtils(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * @Description:
     * @Param: key key 键
     * time 失效 时间 ， 单 位秒
     * @return:
     * @date: 2020/3/9
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.error("-> 设置 redis key 失效时间异常 " + e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @Description: 查询 ke 过 期 时间
     * @Param:
     * @return: 时间 ( 秒 ) 返回 0 代表 为 永久有效
     * @date: 2020/3/9
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * @Description: 删 除 缓 存
     * @Param: 集合，多 个 一起 处 理
     * @return:
     * @date: 2020/3/9
     */
    public void delKeys(String key) {
        if (key != null) {
            redisTemplate.delete(key);
        }
    }

    /**
     * @Description: 删 除 缓 存
     * @Param: 单个进 行 删 除
     * @return:
     * @date: 2020/3/9
     */
    public boolean del(String key) {
        if (!StringUtils.isEmpty(key)) {
            return redisTemplate.delete(key);
        }
        return true;
    }

    /**
     * @Description: 持久化 key 的 值
     * @Param:
     * @return:
     * @date: 2020/3/11
     */
    public boolean persist(String key) {
        return redisTemplate.persist(key);
    }

//String 结构类型处理

    /**
     * @Description: 普通 缓 存 获 取
     * @Param:
     * @return:
     * @date: 2020/3/9
     */
    public Object get(String key) {
        return StringUtils.isEmpty(key) ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * @Description: 普通 缓 存存 储
     * @Param:
     * @return:
     * @date: 2020/3/9
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("->set occur error " + e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param key   键
     * @param value 值
     * @param time  时间 ( 秒 ) time 要大于 0 如果 time 小于等于 0 将设 置无限期
     * @Description: 普通 缓 存存 储 + 设 置 过 期 时间
     * @return:
     * @date: 2020/3/9
     */
    public boolean set(String key, Object value, long time) {
        try {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            log.error("->set occur error " + e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @Description: incrby 递 增
     * @Param:
     * @return:
     * @date: 2020/3/11
     */
    public long incrby(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException(" 递增因子必须大于 0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * @Description: decryb 递减
     * @Param:
     * @return:
     * @date: 2020/3/11
     */
    public long decrby(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException(" 递减因子必须大于 0");
        }
        return redisTemplate.opsForValue().decrement(key, -delta);
    }

//list 链表结构类型处理
//..

    /**
     * @Description: 查询 key 对应 的 list 缓 存的 内 容
     * @Param:
     * @return:
     * @date: 2020/3/11
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            log.error("lGet occur error. " + e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Description: 查询 List 对应 的 长 度
     * @Param:
     * @return:
     * @date: 2020/3/11
     */
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            log.error("lGetListSize occur error." + e);
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @Description: 通 过 索引 找 到 list 中的 值
     * @Param:
     * @return:
     * @date: 2020/3/11
     */
    public Object lGetByIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            log.error("lGetByIndex occur error. " + e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Description: 将数 据存 储 在 list 缓 存
     * @Param:
     * @return:
     * @date: 2020/3/11
     */
    public boolean rPush(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            log.error("rPush occur error. " + e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @Description:
     * @Param:
     * @return:
     * @date: 2020/3/11
     */
    public boolean rPush(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("rPush with expire occur error. " + e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @Description: 将 list 进 行存 储
     * @Param:
     * @return:
     * @date: 2020/3/11
     */
    public boolean rPush(String key, List<Object> list) {
        try {
            redisTemplate.opsForList().rightPushAll(key, list);
            return true;
        } catch (Exception e) {
            log.error("rPushAll occur error. " + e);
            return false;
        }
    }

    /**
     * @Description: 将 list 进 行存 储 + 过 期 时间
     * @Param:
     * @return:
     * @date: 2020/3/11
     */
    public boolean rPush(String key, List<Object> list, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, list);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("rPushAll occur error. " + e);
            return false;
        }
    }

    /**
     * @Description: 根据索引修改 list 中的某 条数 据
     * @Param:
     * @return:
     * @date: 2020/3/11
     */
    public boolean lUpdateIndexValue(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            log.error("lUpdateIndexValue occur error." + e);
            e.printStackTrace();
            return false;
        }
    }


//HashMap 结构类型处理
//..

    /**
     * @Description: 获 取 key 对应 的 map 指定 profile 的 值
     * @Param:
     * @return:
     * @date: 2020/3/11
     */
    public Object hGet(String key, String profile) {
        return redisTemplate.opsForHash().get(key, profile);
    }

    /**
     * @Description: 获 取 key 对应 的 Hash 值
     * @Param:
     * @return:
     * @date: 2020/3/11
     */
    public Map<Object, Object> hMget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * @Description: 存 储值 HashMap
     * @Param:
     * @return:
     * @date: 2020/3/11
     */
    public boolean hMset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            log.error("->hmset occur error " + e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @Description: 存 储 HashMap + 过 期 时间
     * @Param:
     * @return:
     * @date: 2020/3/11
     */
    public boolean hMset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("->hmset with expire time occur error " + e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @Description: 向一 张 hash 表中放入 数 据 , 如果不存在 将创 建
     * @Param:
     * @return:
     * @date: 2020/3/11
     */
    public boolean hSet(String key, String profiel, Object value, Long time) {
        try {
            redisTemplate.opsForHash().put(key, profiel, value);
            if (time != null && time.longValue() > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("->hset with expire time occur error " + e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @Description: 删 除 Hash 表中的 值
     * @Param:
     * @return:
     * @date: 2020/3/11
     */
    public void hDel(String key, Object... profile) {
        redisTemplate.opsForHash().delete(key, profile);
    }

    /**
     * @Description: 判 断 hash 表中是否有 key 值
     * @Param:
     * @return:
     * @date: 2020/3/11
     */
    public boolean hHasKey(String key, String profile) {
        return redisTemplate.opsForHash().hasKey(key, profile);
    }

}
