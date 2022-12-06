package com.example.businessmanagement2.repository.schedule;

import lombok.Value;

@Value
public class ScheduleEntity {

  Long id;

  Long userId;

  String workingDate;

  String numberOfFloors;

  String place;

  String workContent;

  String numberOfPeople;

}
