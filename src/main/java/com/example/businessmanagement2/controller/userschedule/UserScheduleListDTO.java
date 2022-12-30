package com.example.businessmanagement2.controller.userschedule;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class UserScheduleListDTO {

  List<UserScheduleDTO> results = new ArrayList<>();

}
