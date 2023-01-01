package com.example.businessmanagement2.repository.performance;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PerformanceRepository {

  Optional<PerformanceEntity> findById(Long performanceId);

  List<PerformanceEntity> findPerformanceList();

  void create(PerformanceEntity entity);

  void update(PerformanceEntity entity);

  void delete(Long performanceId);

}
