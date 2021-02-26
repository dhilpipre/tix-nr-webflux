package com.tiket.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Session implements Serializable {

  private String sessionId;
  private String userId;
}