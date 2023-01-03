package com.example.businessmanagement.controller.userschedule;


import com.example.businessmanagement.repository.schedule.ScheduleEntity;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class UserScheduleDTO {

  long userId;

  String companyName;

  String userName;

  List<ScheduleEntity> scheduleEntities;

}
