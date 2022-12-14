package com.example.businessmanagement2.repository.schedule;



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

  @Select("""
          SELECT 
          schedule_id, user_id, working_date, number_of_floors,
          place, work_content, number_of_people,
          FROM schedules WHERE schedule_id = #{scheduleId}
          """)
  Optional<ScheduleEntity> findById(Long scheduleid);

  @Select("SELECT * FROM schedules")
  List<ScheduleEntity> findScheduleList();

  @Options(useGeneratedKeys = true, keyProperty = "id")
  @Insert("""
           INSERT INTO schedules 
           (
           user_Id, working_date, number_of_floors, place, work_content, number_of_people
           ) 
           values 
           (
           #{userId}, #{workingDate}, #{numberOfFloors}, #{place}, #{workContent}, #{numberOfPeople}
           )
           """)
  void create(ScheduleEntity entity);

  @Update("""
           UPDATE schedules  SET 
           schedule_Id = #{scheduleId}, user_Id = #{userId}, working_date = #{workingDate}, number_of_floors = #{numberOfFloors},
           place = #{place}, work_content = #{workContent}, number_of_people = #{numberOfPeople}
           WHERE id = #{id}
           """)
  void update(ScheduleEntity entity);

  @Delete("DELETE from schedules WHERE user_id = #{userId}")
  void delete(long scheduleid);

}
