package com.example.businessmanagement.service.performance;

import com.example.businessmanagement.repository.performance.PerformanceEntity;
import java.time.LocalDate;
import java.util.List;

public interface PerformanceService {

  PerformanceEntity findById(Long performanceId);

  List<PerformanceEntity> findPerformanceList();

  PerformanceEntity create(int userId, LocalDate workingDate, String place, String workContent,
      int numberOfPeople);

  void update(Long performanceId, int userId, LocalDate workingDate, String place,
      String workContent, int numberOfPeople);

  void delete(Long performanceId);

}
