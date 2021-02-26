package com.tiket.libraries;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;


@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "tix.nr.outbound.general.info")
public class GeneralInfoOutboundProperties {

  private String baseUrl;
  protected Integer connectTimeout = 30000;
  protected Integer readTimeout = 30000;
  protected Integer writeTimeout = 30000;

}
