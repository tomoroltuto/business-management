package com.example.businessmanagement.controller.schedule;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class ScheduleListDTO {

  List<ScheduleDTO> results;

}
