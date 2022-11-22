package com.example.businessmanagement2.restcontroller.advice;


import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class BadRequestError {

  private String title = "Bad Request";

  private String detail = "リクエストが不正です。正しいリクエストでリトライしてください";

  private List<InvalidParam> invalidParams = new ArrayList<>();

  public BadRequestError title(String title) {
    this.title = title;
    return this;
  }
}
