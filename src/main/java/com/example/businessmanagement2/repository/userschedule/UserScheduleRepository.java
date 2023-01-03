package com.example.businessmanagement2.repository.userschedule;


import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserScheduleRepository {

  Optional<UserSchedule> findById(long userId);

  List<UserSchedule> findUserScheduleList();

}

