package com.example.businessmanagement2.repository.schedule;

import com.example.businessmanagement2.repository.user.UserEntity;
import java.util.Date;
import lombok.Data;
import lombok.Value;

@Value
public class ScheduleEntity  {

  Long scheduleId;

  int userId;

  Date workingDate;

  String numberOfFloors;

  String place;

  String workContent;

  String numberOfPeople;

}
