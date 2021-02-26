package com.tiket.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BusinessLogicException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final String code;
  private final String message;

  public BusinessLogicException(String code, String message) {
    super();
    this.code = code;
    this.message = message;
  }
}
