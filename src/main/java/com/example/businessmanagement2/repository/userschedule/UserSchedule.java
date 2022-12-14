package com.example.businessmanagement2.repository.userschedule;

import com.example.businessmanagement2.repository.schedule.ScheduleEntity;
import java.util.List;
import lombok.Value;

@Value
public class UserSchedule {

  List<ScheduleEntity> scheduleEntities;

}
