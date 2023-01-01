package com.example.businessmanagement2.repository.schedule;


import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScheduleRepository {

  Optional<ScheduleEntity> findById(Long scheduleId);

  List<ScheduleEntity> findScheduleList();

  void create(ScheduleEntity entity);

  void update(ScheduleEntity entity);

  void delete(Long scheduleId);

}

