package com.example.businessmanagement2.restcontroller.advice;


import lombok.Data;


@Data
public class ResourceNotFoundError {
  private String title = "Resource Not Found";

  private String detail;

  public ResourceNotFoundError title(String title) {
    this.title = title;
    return this;
  }

}
