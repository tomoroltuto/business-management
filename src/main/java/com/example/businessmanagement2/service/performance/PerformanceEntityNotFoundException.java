package com.example.businessmanagement2.service.performance;

public class PerformanceEntityNotFoundException extends RuntimeException{

  private final long performanceId;

  public PerformanceEntityNotFoundException(long performanceId) {
    super("PerformanceEntity (id = " + performanceId + ") is not found.");
    this.performanceId = performanceId;
  }

}
