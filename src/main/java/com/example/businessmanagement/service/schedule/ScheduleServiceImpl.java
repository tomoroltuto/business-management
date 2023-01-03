package com.example.businessmanagement.service.schedule;

import com.example.businessmanagement.repository.schedule.ScheduleEntity;
import com.example.businessmanagement.repository.schedule.ScheduleRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {


  private final ScheduleRepository scheduleRepository;

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
  public ScheduleEntity create(int userId, LocalDate workingDate, String place, String workContent,
      int numberOfPeople) {
    var entity = new ScheduleEntity(null, userId, workingDate, place, workContent, numberOfPeople);
    scheduleRepository.create(entity);
    return entity;
  }

  @Override
  public void update(Long scheduleId, int userId, LocalDate workingDate, String place,
      String workContent, int numberOfPeople) {
    scheduleRepository.findById(scheduleId)
        .orElseThrow(() -> new ScheduleEntityNotFoundException(scheduleId));
    scheduleRepository.update(
        new ScheduleEntity(scheduleId, userId, workingDate, place, workContent, numberOfPeople));
  }

  @Override
  public void delete(Long scheduleId) {
    scheduleRepository.findById(scheduleId)
        .orElseThrow(() -> new ScheduleEntityNotFoundException(scheduleId));
    scheduleRepository.delete(scheduleId);
  }
}
