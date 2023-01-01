package com.example.businessmanagement2.controller.performance;


import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class PerformanceDTO {

  long performanceId;

  int userId;

  @JsonFormat(pattern = "yyyy-MM-dd")
  LocalDate workingDate;

  String place;

  String workContent;

  int numberOfPeople;

}

