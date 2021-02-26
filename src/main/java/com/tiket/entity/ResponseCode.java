package com.tiket.entity;

public enum ResponseCode {
  SUCCESS("SUCCESS", "SUCCESS"),
  SYSTEM_ERROR("SYSTEM_ERROR", "Contact Our Team"),
  DATA_NOT_EXIST("DATA_NOT_FOUND", "data not found"),
  DATA_NOT_VALID("DATA_NOT_VALID", "data not valid");

  private String code;
  private String message;

  ResponseCode(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
