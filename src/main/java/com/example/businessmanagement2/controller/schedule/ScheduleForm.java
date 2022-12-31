package com.example.businessmanagement2.controller.schedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
  @JsonFormat(pattern = "yyyy-MM-dd")
  @FutureOrPresent
  LocalDate workingDate;

  @Size(max = 256)
  String place;

  @NotBlank
  @Size(max = 256)
  String workContent;

  @Min(1)
  @Max(200)
  int numberOfPeople;

}
