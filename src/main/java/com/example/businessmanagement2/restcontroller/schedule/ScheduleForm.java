package com.example.businessmanagement2.restcontroller.schedule;

import java.util.Date;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScheduleForm {

  int userId;

  @NotBlank
  @Pattern(regexp = "^[ -~｡-ﾟ]*$")
  @FutureOrPresent
  Date workingDate;

  String numberOfFloors;

  String place;

  @NotBlank
  @Size(min = 1, max = 256)
  String workContent;

  @NotBlank
  @Size(min = 1, max = 3)
  String numberOfPeople;

}
