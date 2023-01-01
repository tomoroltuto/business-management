package com.example.businessmanagement2.controller.advice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class BadRequestErrorCreator {

  public static BadRequestError from(MethodArgumentNotValidException ex) {
    var invalidParamList = createInvalidParamList(
        ex.getFieldErrors().stream().map(BadRequestErrorCreator::createInvalidParam));
    var error = new BadRequestError("Bad Request", "リクエストが不正です。正しいリクエストでリトライしてください",
        new ArrayList<>(invalidParamList));
    return error;
  }

  private static InvalidParam createInvalidParam(FieldError fieldError) {
    var invalidParam = new InvalidParam(fieldError.getField(), fieldError.getDefaultMessage());
    return invalidParam;
  }

  private static List<InvalidParam> createInvalidParamList(Stream<InvalidParam> ex) {
    return ex.collect(Collectors.toList());
  }
}
