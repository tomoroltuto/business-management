package com.example.businessmanagement2.restcontroller.userschedule;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class UserScheduleListDTO {

  List<UserScheduleDTO> results = new ArrayList<>();

}
