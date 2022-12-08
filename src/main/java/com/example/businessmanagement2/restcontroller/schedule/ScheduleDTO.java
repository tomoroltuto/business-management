package com.example.businessmanagement2.restcontroller.schedule;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScheduleDTO {

  long id;

  int userId;

  Date workingDate;

  String numberOfFloors;

  String place;

  String workContent;

  String numberOfPeople;

}
