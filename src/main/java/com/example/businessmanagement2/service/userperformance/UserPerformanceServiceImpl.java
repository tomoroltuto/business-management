package com.example.businessmanagement2.service.userperformance;

import com.example.businessmanagement2.repository.userperformance.UserPerformance;
import com.example.businessmanagement2.repository.userperformance.UserPerformanceRepository;
import com.example.businessmanagement2.repository.userschedule.UserSchedule;
import com.example.businessmanagement2.repository.userschedule.UserScheduleRepository;
import com.example.businessmanagement2.service.user.UserEntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPerformanceServiceImpl implements UserPerformanceService {

  private final UserPerformanceRepository userPerformanceRepository;

  @Override
  public UserPerformance findById(long userId) {
    return userPerformanceRepository.findById(userId)
        .orElseThrow(() -> new UserEntityNotFoundException(userId));
  }

  @Override
  public List<UserPerformance> findUserPerformanceList() {
    return userPerformanceRepository.findUserPerformanceList();
  }
}
