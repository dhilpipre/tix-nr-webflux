package com.tiket.service;

import java.io.IOException;
import java.util.List;

public interface CacheService {

  <T> T findCacheByKey(String key, Class<T> clazz);

  <T> T findCache(String key, Class<T> clazz) throws IOException;

  <T> Boolean createCache(String key, T value, long expirySeconds);

  Boolean deleteCache(String key);

  Boolean deleteCache(List<String> keys);

  <T> Boolean createIfNotExists(
      String key,
      T value,
      long expiryTimeInSeconds);

  <T> Boolean updateValue(
      String key,
      T value);

  Long findExpiry(
      String key);

  List<String> findKeysByPattern(
      String pattern);

  List<String> findValues(
      List<String> keys);

  Boolean hasKey(String key);
}
