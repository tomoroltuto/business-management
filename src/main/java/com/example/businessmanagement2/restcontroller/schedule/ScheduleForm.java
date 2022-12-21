package com.example.businessmanagement2.restcontroller.schedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
public class ScheduleForm {

  int userid;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy/MM/dd")
  @FutureOrPresent
  Date workingdate;

  String numberoffloors;

  String place;

  @NotBlank
  @Size(min = 1, max = 256)
  String workcontent;

  @NotBlank
  @Size(min = 1, max = 3)
  String numberofpeople;

}
