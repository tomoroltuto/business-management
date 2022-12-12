package com.example.businessmanagement2.restcontroller.schedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScheduleDTO {

  long id;

  int userid;

  @JsonFormat(pattern = "yyyy-MM-dd")
  Date workingdate;

  String numberoffloors;

  String place;

  String workcontent;

  String numberofpeople;

}
