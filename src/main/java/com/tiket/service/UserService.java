package com.tiket.service;

import com.tiket.entity.User;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UserService {

  String login();
  Mono<List<User>> findAll();
  Mono<List<User>> findAllRedis();
  Mono<List<User>> findAllWebClient();
  Mono<List<User>> findAllZip();
  Mono<List<User>> findAllNonReactor();
}

