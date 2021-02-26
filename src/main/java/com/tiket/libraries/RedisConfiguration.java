package com.tiket.libraries;

import lombok.Data;

@Data
class RedisConfiguration {

  private String host;
  private int port;
  private String password;
  private int minIdle;
  private int maxIdle;
  private long maxWaitMillis;
  private int maxActive;
  private long readTimeout;
  private long connectTimeout;

}
