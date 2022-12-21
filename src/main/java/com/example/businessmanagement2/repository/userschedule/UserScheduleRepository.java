package com.example.businessmanagement2.repository.userschedule;


import com.example.businessmanagement2.repository.user.UserEntity;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserScheduleRepository {

  Optional<UserSchedule> findById(long userid);

  List<UserSchedule> findUserScheduleList();


}
