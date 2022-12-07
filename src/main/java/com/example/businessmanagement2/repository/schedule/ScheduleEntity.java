package com.example.businessmanagement2.repository.schedule;

import lombok.Value;

@Value
public class ScheduleEntity {

  int userId;

  String workingDate;

  String numberOfFloors;

  String place;

  String workContent;

  String numberOfPeople;

}
