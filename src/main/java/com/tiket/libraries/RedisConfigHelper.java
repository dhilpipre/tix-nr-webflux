package com.tiket.libraries;

import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.time.Duration;

/**
 * Created by ito on Apr 09, 2019
 */
class RedisConfigHelper {

  private RedisConfigHelper() {}

  static JedisConnectionFactory getJedisConnectionFactory(
      String host, int port, String password, long connectTimeout, long readTimeout) {
    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
    redisStandaloneConfiguration.setHostName(host);
    redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
    redisStandaloneConfiguration.setPort(port);
    JedisClientConfiguration jedisClientConfiguration =
        JedisClientConfiguration.builder()
            .connectTimeout(Duration.ofMillis(connectTimeout))
            .readTimeout(Duration.ofMillis(readTimeout))
            .usePooling().build();

    return new JedisConnectionFactory(redisStandaloneConfiguration,jedisClientConfiguration);
  }

}
