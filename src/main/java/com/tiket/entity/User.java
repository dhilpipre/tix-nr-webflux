package com.tiket.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

  private String email;
  private String phone;
  private String password;
}