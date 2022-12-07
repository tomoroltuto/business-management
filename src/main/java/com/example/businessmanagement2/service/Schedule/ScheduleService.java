package com.example.businessmanagement2.service.Schedule;

import com.example.businessmanagement2.repository.schedule.ScheduleEntity;
import com.example.businessmanagement2.repository.user.UserEntity;
import java.util.Date;

public interface ScheduleService {

  ScheduleEntity create(
      int userId, Date workingDate, String numberOfFloors, String place,
      String workContent, String numberOfPeople);

  ScheduleEntity update(
      int userId, Date workingDate, String numberOfFloors, String place,
      String workContent, String numberOfPeople);

  void delete(Long ScheduleId);

}
