package com.tiket.service.impl;

import com.tiket.entity.User;
import com.tiket.service.CacheService;
import com.tiket.service.GeneralInfoOutboundService;
import com.tiket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private CacheService cacheService;

  @Autowired
  private GeneralInfoOutboundService generalInfoOutboundService;

  @Override
  public String login() {
    return null;
  }

  @Override
  public Mono<List<User>> findAll() {
    return checkSession()
        .flatMap(valid -> createCache());
  }

  @Override
  public Mono<List<User>> findAllRedis() {
    return checkSession()
        .map(valid -> {

          try {
            Thread.sleep(400);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

          cacheService.createCache("tix-nr-webflux", "tes", 300l);
          return Collections.emptyList();
        });
  }

  @Override
  public Mono<List<User>> findAllWebClient() {
    return checkSession()
        .flatMap(valid ->
          generalInfoOutboundService.getGeneralInfo());
  }

  @Override
  public Mono<List<User>> findAllZip() {

    return Mono.zip(
        checkSession(),
        createCache())
        .map(objects -> Collections.emptyList());
  }

  @Override
  public Mono<List<User>> findAllNonReactor() {
    return checkSession()
        .map(valid -> createCacheNonReactor());
  }

  private Mono<Boolean> checkSession(){

    return Mono.defer(() -> {

      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      return Mono.just(true);
    });
  }

  private Mono<List<User>> createCache(){

    return Mono.defer(() -> {

      try {
        Thread.sleep(400);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      return Mono.just(Collections.<User>emptyList());
    });
  }

  private List<User> createCacheNonReactor(){

    try {
      Thread.sleep(350);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return Collections.<User>emptyList();
  }

}
