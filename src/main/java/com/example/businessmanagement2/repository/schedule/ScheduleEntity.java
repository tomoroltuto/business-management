package com.example.businessmanagement2.repository.schedule;


import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
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
