package com.example.businessmanagement2.service.Schedule;

import com.example.businessmanagement2.repository.schedule.ScheduleEntity;
import com.example.businessmanagement2.repository.user.UserEntity;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ScheduleService {

  ScheduleEntity findById(Long scheduleId);

  List<ScheduleEntity> findScheduleList();

  ScheduleEntity create(
      int userId, Date workingDate, String numberOfFloors, String place,
      String workContent, String numberOfPeople);

  ScheduleEntity update(
      Long scheduleId, int userId, Date workingDate, String numberOfFloors, String place,
      String workContent, String numberOfPeople);

  void delete(Long scheduleId);

}
