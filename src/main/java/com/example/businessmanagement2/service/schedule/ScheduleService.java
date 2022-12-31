package com.example.businessmanagement2.service.schedule;

import com.example.businessmanagement2.repository.schedule.ScheduleEntity;
import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {

  ScheduleEntity findById(Long scheduleId);

  List<ScheduleEntity> findScheduleList();

  ScheduleEntity create(
      int userId, LocalDate workingDate, String place,
      String workContent, int numberOfPeople);

  ScheduleEntity update(
      Long scheduleId, int userId, LocalDate workingDate, String place,
      String workContent, int numberOfPeople);

  void delete(Long scheduleId);
}
