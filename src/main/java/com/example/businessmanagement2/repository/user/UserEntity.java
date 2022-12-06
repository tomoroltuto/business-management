package com.example.businessmanagement2.repository.user;


import com.example.businessmanagement2.repository.schedule.ScheduleEntity;
import java.util.List;
import lombok.Value;

@Value
public class UserEntity {

  Long id;

  String companyname;

  String username;

  List<ScheduleEntity> scheduleEntities;

}
