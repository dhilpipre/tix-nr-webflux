package com.tiket.entity;

import com.tiket.entity.response.BaseResponse;

import java.util.List;

public class CommonResponse {
  public CommonResponse() {
  }

  public static <T> BaseResponse<T> constructResponse(String code, String message, List<String> errors, T data) {
    return BaseResponse.<T>builder().code(code).message(message).errors(errors).data(data).build();
  }
}
