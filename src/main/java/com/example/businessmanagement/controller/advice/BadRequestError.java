package com.example.businessmanagement.controller.advice;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class BadRequestError {

  String title;

  String detail;

  List<InvalidParam> invalidParams;

}
