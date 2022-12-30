package com.example.businessmanagement2.service.Schedule;

import com.example.businessmanagement2.repository.schedule.ScheduleEntity;
import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {

  ScheduleEntity findById(Long scheduleid);

  List<ScheduleEntity> findScheduleList();

  ScheduleEntity create(
      int userId, LocalDate workingdate, String place,
      String workcontent, int numberofpeople);

  ScheduleEntity update(
      Long scheduleId, int userId, LocalDate workingdate, String place,
      String workcontent, int numberofpeople);

  void delete(Long scheduleId);
}
