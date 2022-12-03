package com.example.businessmanagement2.restcontroller.advice;


import lombok.Data;


@Data
public class ResourceNotFoundError {

  private String title = "Resource Not Found";

  private String detail;

}
