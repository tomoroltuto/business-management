package com.example.businessmanagement2.repository.userschedule;

import com.example.businessmanagement2.repository.schedule.ScheduleEntity;
import com.example.businessmanagement2.repository.user.UserEntity;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSchedule {

  Long userid;

  String companyname;

  String username;


  ScheduleEntity scheduleEntitys;

}
