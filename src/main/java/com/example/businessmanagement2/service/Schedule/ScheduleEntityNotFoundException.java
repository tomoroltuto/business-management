package com.example.businessmanagement2.service.Schedule;

public class ScheduleEntityNotFoundException extends RuntimeException {

  private final long ScheduleId;

  public ScheduleEntityNotFoundException(long ScheduleId) {
    super("ScheduleEntity (id = " + ScheduleId + ") is not found.");
    this.ScheduleId = ScheduleId;
  }

}
