package com.example.businessmanagement2.service.Schedule;

import com.example.businessmanagement2.repository.schedule.ScheduleEntity;
import java.util.Date;
import java.util.List;

public interface ScheduleService {

  ScheduleEntity findById(Long scheduleid);

  List<ScheduleEntity> findScheduleList();

  ScheduleEntity create(
      int userId, Date workingdate, String numberoffloors, String place,
      String workcontent, String numberofpeople);

  ScheduleEntity update(
      Long scheduleId, int userId, Date workingdate, String numberoffloors, String place,
      String workcontent, String numberofpeople);

  void delete(Long scheduleId);
}
