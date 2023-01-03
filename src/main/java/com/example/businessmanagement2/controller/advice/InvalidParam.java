package com.example.businessmanagement2.controller.advice;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class InvalidParam {

  private String name;

  private String reason;

}

