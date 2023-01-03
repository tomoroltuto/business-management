package com.example.businessmanagement.service.userschedule;

import com.example.businessmanagement.repository.userschedule.UserSchedule;
import com.example.businessmanagement.repository.userschedule.UserScheduleRepository;
import com.example.businessmanagement.service.user.UserEntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserScheduleServiceImpl implements UserScheduleService {

  private final UserScheduleRepository userScheduleRepository;

  @Override
  public UserSchedule findById(long userId) {
    return userScheduleRepository.findById(userId)
        .orElseThrow(() -> new UserEntityNotFoundException(userId));
  }

  @Override
  public List<UserSchedule> findUserScheduleList() {
    return userScheduleRepository.findUserScheduleList();
  }
}

