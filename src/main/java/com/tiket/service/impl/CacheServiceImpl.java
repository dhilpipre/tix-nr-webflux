package com.tiket.service.impl;

import com.tiket.common.CommonConstant;
import com.tiket.service.CacheService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class CacheServiceImpl implements CacheService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CacheServiceImpl.class);

  @Autowired
  private RedisTemplate redisTemplate;

  @Override
  public <T> T findCacheByKey(String key, Class<T> clazz) {
    T readValue = null;
    try {
      Object content = this.redisTemplate.opsForValue().get(key);
      if (content != null) {
        readValue = clazz.cast(content);
      }
    } catch (Exception e) {
      LOGGER.warn("#failed-to-getCachedValue for key :{}, class:{}, error: {}", key, clazz,
          e.getMessage(), e);
    }
    return readValue;
  }

  @Override
  public <T> T findCache(String key, Class<T> clazz) {
    Object content = this.redisTemplate.opsForValue().get(key);
    if (content != null) {
      return clazz.cast(content);
    }

    return null;
  }

  @Override
  public <T> Boolean createCache(String key, T value, long expirySeconds) {
    Boolean success = true;

    try {

      this.redisTemplate.opsForValue().set(key, value);
      if (expirySeconds > 0) {
        this.redisTemplate.expire(key, expirySeconds, TimeUnit.SECONDS);
      }
    } catch (Exception e) {
      LOGGER.error("CacheServiceImpl-createCache stackTrace = {}",
          e);
      success = false;
    }

    return success;
  }

  @Override
  public Boolean deleteCache(String key) {
    Boolean success = true;

    try {
      this.redisTemplate.delete(key);
    } catch (Exception e) {
      LOGGER.error("CacheServiceImpl-deleteCache stackTrace = {}",
          e);
      success = false;
    }

    return success;
  }

  @Override
  public Boolean deleteCache(List<String> keys) {
    Boolean success = true;

    try {
      this.redisTemplate.delete(keys);
    } catch (Exception e) {
      LOGGER.error("CacheServiceImpl-deleteCache stackTrace = {}",
          e);
      success = false;
    }

    return success;
  }

  @Override
  public <T> Boolean createIfNotExists(
      String key,
      T value,
      long expiryTimeInSeconds) {

    if (!setIfAbsent(key, value)) {
      return false;
    }

    setExpiry(key, expiryTimeInSeconds);
    return true;
  }

  @Override
  public <T> Boolean updateValue(
      String key,
      T value) {

    Object valueFromCache = findValue(key);
    if (valueFromCache == null) {
      return false;
    }

    Long expiryTimeInSeconds = findExpiry(key);
    setValue(
        key,
        value);

    setExpiry(
        key,
        expiryTimeInSeconds);

    return true;
  }

  @Override
  public Long findExpiry(
      String key) {
    return redisTemplate.getExpire(key);
  }

  private <T> Boolean setIfAbsent(
      String key,
      T value) {
    try {
      return redisTemplate.opsForValue()
          .setIfAbsent(key, value);
    } catch (Exception e) {
      LOGGER.warn("Failed to setIfAbsent - ", e);
      return false;
    }
  }


  private <T> void setValue(
      String key,
      T value) {
    try {
      redisTemplate.opsForValue()
          .set(key, value);
    } catch (Exception e) {
      LOGGER.warn("Failed to set to redis - ", e);
    }
  }

  private void setExpiry(
      String key,
      long expiryTimeInSeconds) {

    if (expiryTimeInSeconds > 0) {
      redisTemplate.expire(
          key,
          expiryTimeInSeconds,
          TimeUnit.SECONDS);
    }
  }

  private Object findValue(String key) {
    return redisTemplate.opsForValue()
        .get(key);
  }

  @Override
  public List<String> findKeysByPattern(
      String pattern) {

    Set<String> keys = redisTemplate.keys(
        StringUtils.defaultIfEmpty(
            pattern,
            CommonConstant.PATTERN_ALL));
    return new ArrayList<>(keys);
  }

  @Override
  public List<String> findValues(
      List<String> keys) {

    return redisTemplate.opsForValue()
        .multiGet(keys);
  }

  @Override
  public Boolean hasKey(String key) {
    return redisTemplate.hasKey(key);
  }

}