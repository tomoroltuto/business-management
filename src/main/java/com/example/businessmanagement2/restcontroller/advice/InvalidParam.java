package com.example.businessmanagement2.restcontroller.advice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Data
public class InvalidParam {
  private String name;

  private String reason;

  public InvalidParam name(String name) {
    this.name = name;
    return this;
  }
}
