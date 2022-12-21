package com.example.businessmanagement2.restcontroller.userschedule;

import com.example.businessmanagement2.restcontroller.user.UserDTO;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserScheduleListDTO {

  List<UserScheduleDTO> results = new ArrayList<>();

}
