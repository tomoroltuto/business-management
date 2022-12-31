package com.example.businessmanagement2.controller.userschedule;


import com.example.businessmanagement2.repository.schedule.ScheduleEntity;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserScheduleDTO {

  Long userid;

  String companyName;

  String userName;

  List<ScheduleEntity> scheduleEntitys;

}
