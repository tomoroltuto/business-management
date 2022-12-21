package com.example.businessmanagement2.repository.schedule;


import com.example.businessmanagement2.repository.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleEntity {

  Long scheduleid;

  int userid;

  @JsonFormat(pattern = "yyyy-MM-dd")
  Date workingdate;

  String numberoffloors;

  String place;

  String workcontent;

  String numberofpeople;

}
