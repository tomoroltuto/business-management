package com.example.businessmanagement2.repository.userschedule;

import com.example.businessmanagement2.repository.schedule.ScheduleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSchedule {

  Long userid;

  String companyname;

  String username;

  ScheduleEntity scheduleEntitys;

}
