package com.example.businessmanagement2.repository.schedule;


import com.example.businessmanagement2.repository.user.UserEntity;
import java.util.Date;

import lombok.Value;

@Value
public class ScheduleEntity  {

  Long scheduleid;

  int userid;

  Date workingdate;

  String numberoffloors;

  String place;

  String workcontent;

  String numberofpeople;

}
