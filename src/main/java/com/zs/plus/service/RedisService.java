package com.zs.plus.service;

import com.alibaba.fastjson.JSON;
import com.zs.plus.config.prefix.KeyPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {
  
  @Autowired
  JedisPool jedisPool;
  
  /**
   * 获取key-value
   *
   * @param prefix
   * @param key
   * @param clazz
   * @param <T>
   * @return
   */
  public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      String realKey = prefix.getPrefix() + key;
      String str = jedis.get(realKey);
      return stringToBean(str, clazz);
    } finally {
      returnToPool(jedis);
    }
  }
  
  /**
   * 设置key-value
   *
   * @param keyPrefix
   * @param key
   * @param value
   * @param <T>
   * @return
   */
  public <T> boolean set(KeyPrefix keyPrefix, String key, T value) {
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      String str = beanToString(value);
      if (str == null || str.length() == 0) {
        return false;
      }
      String realKey = keyPrefix.getPrefix() + key;
      int seconds = keyPrefix.expireSeconds();
      if (seconds <= 0) {
        jedis.set(realKey, str);
      } else {
        jedis.setex(realKey, seconds, str);
      }
      return true;
    } finally {
      returnToPool(jedis);
    }
  }
  
  /**
   * 判断key是否存在
   *
   * @param keyPrefix
   * @param key
   * @param <T>
   * @return
   */
  public <T> boolean exists(KeyPrefix keyPrefix, String key) {
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      String realKey = keyPrefix.getPrefix() + key;
      return jedis.exists(realKey);
    } finally {
      returnToPool(jedis);
    }
  }
  
  /**
   * 增加值
   *
   * @param prefix
   * @param key
   * @param <T>
   * @return
   */
  public <T> Long incr(KeyPrefix prefix, String key) {
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      //生成真正的key
      String realKey = prefix.getPrefix() + key;
      return jedis.incr(realKey);
    } finally {
      returnToPool(jedis);
    }
  }
  
  /**
   * 减少值
   *
   * @param prefix
   * @param key
   * @param <T>
   * @return
   */
  public <T> Long decr(KeyPrefix prefix, String key) {
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      //生成真正的key
      String realKey = prefix.getPrefix() + key;
      return jedis.decr(realKey);
    } finally {
      returnToPool(jedis);
    }
  }
  
  /**
   * bean转换为string
   *
   * @param value
   * @param <T>
   * @return
   */
  private <T> String beanToString(T value) {
    if (value == null) {
      return null;
    }
    Class<?> clazz = value.getClass();
    if (clazz == int.class || clazz == Integer.class) {
      return "" + value;
    } else if (clazz == String.class) {
      return (String) value;
    } else if (clazz == long.class || clazz == Long.class) {
      return "" + value;
    } else {
      return JSON.toJSONString(value);
    }
  }
  
  /**
   * String转换为bean
   *
   * @param str
   * @param clazz
   * @param <T>
   * @return
   */
  private <T> T stringToBean(String str, Class<T> clazz) {
    if (str == null || str.length() <= 0 || clazz == null) {
      return null;
    }
    if (clazz == int.class || clazz == Integer.class) {
      return (T) Integer.valueOf(str);
    } else if (clazz == String.class) {
      return (T) str;
    } else if (clazz == long.class || clazz == Long.class) {
      return (T) Long.valueOf(str);
    } else {
      return JSON.toJavaObject(JSON.parseObject(str), clazz);
    }
  }
  
  /**
   * 关闭jedis
   *
   * @param jedis
   */
  private void returnToPool(Jedis jedis) {
    if (jedis != null) {
      jedis.close();
    }
  }
  
}
