package com.example.businessmanagement2.repository.user;


import com.example.businessmanagement2.repository.schedule.ScheduleEntity;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@AllArgsConstructor
public class UserEntity {

  Long userid;

  String companyname;

  String username;

}
