package com.example.businessmanagement2.repository.userschedule;

import com.example.businessmanagement2.repository.schedule.ScheduleEntity;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSchedule {

  Long userid;

  String companyName;

  String userName;

  List<ScheduleEntity> scheduleEntitys;

}
