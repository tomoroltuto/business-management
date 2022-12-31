package com.example.businessmanagement2.service.schedule;

import com.example.businessmanagement2.repository.schedule.ScheduleEntity;
import com.example.businessmanagement2.repository.schedule.ScheduleRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {


  private final ScheduleRepository scheduleRepository;

  @Override
  public ScheduleEntity findById(Long scheduleid) {
    return scheduleRepository.findById(scheduleid)
        .orElseThrow(() -> new ScheduleEntityNotFoundException(scheduleid));
  }

  @Override
  public List<ScheduleEntity> findScheduleList() {
    return scheduleRepository.findScheduleList();
  }

  @Override
  public ScheduleEntity create(int userId, LocalDate workingDate, String place,
      String workContent, int numberOfPeople) {
    var entity = new ScheduleEntity(null, userId, workingDate, place,
        workContent, numberOfPeople);
    scheduleRepository.create(entity);
    return new ScheduleEntity(entity.getScheduleid(), entity.getUserid(), entity.getWorkingDate(),
        entity.getPlace(), entity.getWorkContent(), entity.getNumberOfPeople());
  }

  @Override
  public ScheduleEntity update(Long scheduleId, int userId, LocalDate workingDate, String place,
      String workContent, int numberOfPeople) {
    scheduleRepository.findById(scheduleId)
        .orElseThrow(() -> new ScheduleEntityNotFoundException(scheduleId));
    scheduleRepository.update(new ScheduleEntity(scheduleId, userId, workingDate, place,
        workContent, numberOfPeople));
    return findById(scheduleId);
  }

  @Override
  public void delete(Long scheduleid) {
    scheduleRepository.findById(scheduleid)
        .orElseThrow(() -> new ScheduleEntityNotFoundException(scheduleid));
    scheduleRepository.delete(scheduleid);
  }
}
