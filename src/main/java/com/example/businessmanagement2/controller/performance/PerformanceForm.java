package com.example.businessmanagement2.controller.performance;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class PerformanceForm {

  int userId;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd")
  @PastOrPresent
  LocalDate workingDate;

  @Size(max = 256)
  String place;

  @NotBlank
  @Size(max = 256)
  String workContent;

  @Max(200)
  int numberOfPeople;

}
