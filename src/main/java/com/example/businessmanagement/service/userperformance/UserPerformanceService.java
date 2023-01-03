package com.example.businessmanagement.service.userperformance;

import com.example.businessmanagement.repository.userperformance.UserPerformance;
import java.util.List;

public interface UserPerformanceService {

  UserPerformance findById(long userId);

  List<UserPerformance> findUserPerformanceList();

}
