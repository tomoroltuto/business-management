package com.example.businessmanagement2.controller.schedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScheduleDTO {

  long id;

  int userid;

  @JsonFormat(pattern = "yyyy-MM-dd")
  LocalDate workingDate;

  String place;

  String workContent;

  int numberOfPeople;

}
