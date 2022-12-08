package com.example.businessmanagement2.service.Schedule;

import com.example.businessmanagement2.repository.schedule.ScheduleEntity;
import com.example.businessmanagement2.repository.schedule.ScheduleRepository;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {


  private ScheduleRepository scheduleRepository;

  @Override
  public ScheduleEntity findById(Long scheduleId) {
    return scheduleRepository.findById(scheduleId)
        .orElseThrow(() -> new ScheduleEntityNotFoundException(scheduleId));
  }

  @Override
  public List<ScheduleEntity> findScheduleList() {
    return scheduleRepository.findScheduleList();
  }

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
  public ScheduleEntity update(Long scheduleId, int userId, Date workingDate, String numberOfFloors, String place,
      String workContent, String numberOfPeople) {
    scheduleRepository.findById(scheduleId)
        .orElseThrow(() -> new ScheduleEntityNotFoundException(scheduleId));
    scheduleRepository.update(new ScheduleEntity(scheduleId, userId, workingDate, numberOfFloors, place,
        workContent, numberOfPeople));
    return findById(scheduleId);
  }
  @Override
  public void delete(Long scheduleId) {
    scheduleRepository.findById(scheduleId)
        .orElseThrow(() -> new ScheduleEntityNotFoundException(scheduleId));
    scheduleRepository.delete(scheduleId);
  }
}
