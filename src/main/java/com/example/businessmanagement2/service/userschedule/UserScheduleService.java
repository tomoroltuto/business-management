package com.example.businessmanagement2.service.userschedule;

import com.example.businessmanagement2.repository.userschedule.UserSchedule;
import java.util.List;

public interface UserScheduleService {

  UserSchedule findById(long userid);


  List<UserSchedule> findUserScheduleList();


}
