package com.example.businessmanagement2.restcontroller.schedule;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ScheduleListDTO {

  List<ScheduleDTO> results = new ArrayList<>();

}
