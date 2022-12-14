package com.example.businessmanagement2.service.Schedule;

public class ScheduleEntityNotFoundException extends RuntimeException {

  private final long scheduleId;

  public ScheduleEntityNotFoundException(long scheduleId) {
    super("ScheduleEntity (id = " + scheduleId + ") is not found.");
    this.scheduleId = scheduleId;
  }

}
