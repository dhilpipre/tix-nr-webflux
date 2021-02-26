package com.tiket.service;

import com.tiket.entity.User;
import reactor.core.publisher.Mono;

import java.util.List;

public interface GeneralInfoOutboundService {

  Mono<List<User>> getGeneralInfo();

}
