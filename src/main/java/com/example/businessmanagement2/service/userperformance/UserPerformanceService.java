package com.example.businessmanagement2.service.userperformance;

import com.example.businessmanagement2.repository.userperformance.UserPerformance;
import java.util.List;

public interface UserPerformanceService {

  UserPerformance findById(long userId);

  List<UserPerformance> findUserPerformanceList();

}
