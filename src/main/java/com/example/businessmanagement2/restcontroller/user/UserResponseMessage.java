package com.example.businessmanagement2.restcontroller.user;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserResponseMessage {

  private String message;

  public UserResponseMessage message(String message) {
    this.message = message;
    return this;
  }

}
