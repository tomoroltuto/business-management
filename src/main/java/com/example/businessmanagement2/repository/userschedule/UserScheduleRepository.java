package com.example.businessmanagement2.repository.userschedule;


import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserScheduleRepository {

  @Select("""
          SELECT * FROM users INNER JOIN schedules
          ON users.user_id = schedules.user_id 
          WHERE users.user_id = #{userId}
          """)
  Optional<UserSchedule> findById(long userId);


}
