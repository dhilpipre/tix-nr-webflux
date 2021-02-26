package com.tiket.service;


import com.tiket.entity.Session;

public interface SessionService {

  Session checkSession(String sessionId);
  String createSession(String userId);
}

