package com.example.businessmanagement2.service.Schedule;

import com.example.businessmanagement2.repository.schedule.ScheduleEntity;
import com.example.businessmanagement2.repository.schedule.ScheduleRepository;
import com.example.businessmanagement2.repository.user.UserEntity;
import com.example.businessmanagement2.service.user.UserEntityNotFoundException;
import java.util.Date;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {


  private ScheduleRepository scheduleRepository;

  @Override
  public ScheduleEntity create(int userId, Date workingDate, String numberOfFloors, String place,
      String workContent, String numberOfPeople) {
    var entity = new ScheduleEntity(null, userId, workingDate, numberOfFloors, place,
        workContent,numberOfPeople);
    scheduleRepository.create(entity);
    return new ScheduleEntity(entity.getScheduleId(), entity.getUserId(), entity.getWorkingDate(),entity.getNumberOfFloors(),
    entity.getPlace(), entity.getWorkContent(), entity.getNumberOfPeople());
  }


  @Override
  public ScheduleEntity update(int userId, Date workingDate, String numberOfFloors, String place,
      String workContent, String numberOfPeople) {
    return null;
  }

  @Override
  public void delete(Long ScheduleId) {

  }
}
