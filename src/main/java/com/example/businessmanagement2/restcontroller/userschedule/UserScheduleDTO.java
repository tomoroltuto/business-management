package com.example.businessmanagement2.restcontroller.userschedule;


import com.example.businessmanagement2.repository.schedule.ScheduleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserScheduleDTO {

  Long userid;

  String companyname;

  String username;

  ScheduleEntity scheduleEntitys;

}
