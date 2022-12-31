package com.example.businessmanagement2.repository.userschedule;

import com.example.businessmanagement2.repository.schedule.ScheduleEntity;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserSchedule {

  Long userId;

  String companyName;

  String userName;

  List<ScheduleEntity> scheduleEntities;

}
