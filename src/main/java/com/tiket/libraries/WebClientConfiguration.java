package com.tiket.libraries;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfiguration {


  @Autowired
  GeneralInfoOutboundProperties generalInfoOutboundProperties;

  @Bean("generalInfoWebClient")
  public WebClient generalInfoWebClient() {
    return Optional.of(generalInfoOutboundProperties)
        .map(this::reactorClientHttpConnector)
        .map(connector -> WebClient.builder()
            .clientConnector(connector)
            .baseUrl(generalInfoOutboundProperties.getBaseUrl())
            .build())
        .orElse(WebClient.builder().build());
  }

  private ReactorClientHttpConnector reactorClientHttpConnector(
      GeneralInfoOutboundProperties generalInfoOutboundProperties) {
    return new ReactorClientHttpConnector(opt -> HttpClient.from(TcpClient.create()
        .wiretap(true)
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, generalInfoOutboundProperties.getConnectTimeout())
        .doOnConnected(connection -> connection.addHandlerLast(
            new ReadTimeoutHandler(generalInfoOutboundProperties.getReadTimeout(), TimeUnit.MILLISECONDS))
            .addHandlerLast(
                new WriteTimeoutHandler(generalInfoOutboundProperties.getWriteTimeout(),
                    TimeUnit.MILLISECONDS)))));
  }
}
