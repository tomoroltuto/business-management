package com.example.businessmanagement.service.userschedule;

import com.example.businessmanagement.repository.userschedule.UserSchedule;
import java.util.List;

public interface UserScheduleService {

  UserSchedule findById(long userId);

  List<UserSchedule> findUserScheduleList();

}

