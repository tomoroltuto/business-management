package com.example.businessmanagement2.repository.userperformance;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserPerformanceRepository {

  Optional<UserPerformance> findById(long userId);

  List<UserPerformance> findUserPerformanceList();

}
