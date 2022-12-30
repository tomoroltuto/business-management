package com.example.businessmanagement2.controller.advice;


import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class BadRequestError {

  String title = "Bad Request";

  String detail = "リクエストが不正です。正しいリクエストでリトライしてください";

  List<InvalidParam> invalidParams = new ArrayList<>();

}
