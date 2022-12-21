package com.example.businessmanagement2.service.userschedule;

import com.example.businessmanagement2.repository.userschedule.UserSchedule;
import com.example.businessmanagement2.repository.userschedule.UserScheduleRepository;
import com.example.businessmanagement2.service.user.UserEntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserScheduleServiceImpl implements UserScheduleService {

  private final UserScheduleRepository userScheduleRepository;

  @Override
  public UserSchedule findById(long userid) {
    return userScheduleRepository.findById(userid)
        .orElseThrow(() -> new UserEntityNotFoundException(userid));
  }

  @Override
  public List<UserSchedule> findUserScheduleList() {
    return userScheduleRepository.findUserScheduleList();
  }

}
