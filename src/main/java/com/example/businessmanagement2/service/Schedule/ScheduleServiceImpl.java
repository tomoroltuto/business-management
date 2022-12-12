package com.example.businessmanagement2.service.Schedule;

import com.example.businessmanagement2.repository.schedule.ScheduleEntity;
import com.example.businessmanagement2.repository.schedule.ScheduleRepository;
import java.util.Date;
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
  public ScheduleEntity create(int userId, Date workingdate, String numberoffloors, String place,
      String workcontent, String numberofpeople) {
    var entity = new ScheduleEntity(null, userId, workingdate, numberoffloors, place,
        workcontent,numberofpeople);
    scheduleRepository.create(entity);
    return new ScheduleEntity(entity.getScheduleid(), entity.getUserid(), entity.getWorkingdate(),entity.getNumberoffloors(),
    entity.getPlace(), entity.getWorkcontent(), entity.getNumberofpeople());
  }

  @Override
  public ScheduleEntity update(Long scheduleId, int userId, Date workingdate, String numberoffloors, String place,
      String workcontent, String numberofpeople) {
    scheduleRepository.findById(scheduleId)
        .orElseThrow(() -> new ScheduleEntityNotFoundException(scheduleId));
    scheduleRepository.update(new ScheduleEntity(scheduleId, userId, workingdate, numberoffloors, place,
        workcontent, numberofpeople));
    return findById(scheduleId);
  }
  @Override
  public void delete(Long scheduleid) {
    scheduleRepository.findById(scheduleid)
        .orElseThrow(() -> new ScheduleEntityNotFoundException(scheduleid));
    scheduleRepository.delete(scheduleid);
  }
}
