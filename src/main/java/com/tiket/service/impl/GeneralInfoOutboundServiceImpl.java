package com.tiket.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiket.entity.User;
import com.tiket.libraries.GeneralInfoOutboundProperties;
import com.tiket.service.GeneralInfoOutboundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class GeneralInfoOutboundServiceImpl implements
    GeneralInfoOutboundService {

  public static final String URL = "/find-all";

  @Autowired
  @Qualifier("generalInfoWebClient")
  private WebClient webClient;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  GeneralInfoOutboundProperties generalInfoOutboundProperties;

  @Override
  public Mono<List<User>> getGeneralInfo() {

    try {
      Thread.sleep(350);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }


    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
    headers.put("storeId", Arrays.asList("storeId"));

    return webClient.get()
        .uri(generalInfoOutboundProperties.getBaseUrl() + URL)
        .headers(httpHeaders -> httpHeaders.putAll(headers))
        .exchange()
        .map(clientResponse -> {

          log.info("getGeneralInfo clientResponse: {}", clientResponse.toString());

          return Collections.emptyList();
        });
  }

}
