package com.example.businessmanagement2.restcontroller.userschedule;


import com.example.businessmanagement2.repository.schedule.ScheduleEntity;
import com.example.businessmanagement2.restcontroller.user.UserDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Value;
import org.apache.ibatis.annotations.Select;

@Data
@AllArgsConstructor
public class UserScheduleDTO{

  Long userid;

  String companyname;

  String username;

  ScheduleEntity scheduleEntitys;

}
