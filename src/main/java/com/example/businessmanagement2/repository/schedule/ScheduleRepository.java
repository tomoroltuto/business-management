package com.example.businessmanagement2.repository.schedule;


import com.example.businessmanagement2.repository.user.UserEntity;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ScheduleRepository {


  @Select("SELECT * FROM schedules WHERE id = #{userId}")
  Optional<ScheduleEntity> findById(long userId);

  @Select("SELECT * from schedules")
  List<ScheduleEntity> findUserList();

  @Options(useGeneratedKeys = true, keyProperty = "id")
  @Insert("INSERT INTO schedules (company_name, user_name) values (#{companyname}, #{username})")
  void create(ScheduleEntity entity);

  @Update("UPDATE users SET company_name = #{companyname}, user_name = #{username} WHERE id = #{id}")
  void update(ScheduleEntity entity);

  @Delete("DELETE from users WHERE id = #{userId}")
  void delete(Long userId);

}
