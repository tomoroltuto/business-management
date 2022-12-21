package com.example.businessmanagement2.restcontroller.schedule;

import com.example.businessmanagement2.repository.schedule.ScheduleEntity;
import com.example.businessmanagement2.service.Schedule.ScheduleService;
import java.net.URI;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

  private final ScheduleService scheduleService;

  private static ScheduleDTO toScheduleDTO(ScheduleEntity scheduleEntity) {
    var scheduleDTO = new ScheduleDTO(scheduleEntity.getScheduleid(), scheduleEntity.getUserid(),
        scheduleEntity.getWorkingdate(), scheduleEntity.getNumberoffloors(),
        scheduleEntity.getPlace(), scheduleEntity.getWorkcontent(),
        scheduleEntity.getNumberofpeople());
    scheduleDTO.setId(scheduleEntity.getScheduleid());
    scheduleDTO.setUserid(scheduleEntity.getUserid());
    scheduleDTO.setWorkingdate(scheduleEntity.getWorkingdate());
    scheduleDTO.setNumberoffloors(scheduleEntity.getNumberoffloors());
    scheduleDTO.setPlace(scheduleEntity.getPlace());
    scheduleDTO.setWorkcontent(scheduleDTO.getWorkcontent());
    scheduleDTO.setNumberofpeople(scheduleDTO.getNumberofpeople());
    return scheduleDTO;
  }

  @GetMapping("/schedules/{id}")
  private ResponseEntity<ScheduleDTO> showSchedule(@PathVariable("id") Long ScheduleId) {
    var entity = scheduleService.findById(ScheduleId);
    var dto = toScheduleDTO(entity);
    return ResponseEntity.ok(dto);
  }

  @GetMapping("/schedules")
  private ResponseEntity<ScheduleListDTO> findScheduleList() {
    var entityList = scheduleService.findScheduleList();
    var dtoList = entityList.stream().map(ScheduleController::toScheduleDTO)
        .collect(Collectors.toList());
    var dto = new ScheduleListDTO();
    dto.setResults(dtoList);
    return ResponseEntity.ok(dto);
  }

  @PostMapping("/schedules")
  private ResponseEntity<ScheduleResponseMassage> createSchedule(
      @RequestBody @Validated ScheduleForm form, UriComponentsBuilder uriBuilder) {
    ScheduleEntity se = scheduleService.create(form.getUserid(), form.getWorkingdate(),
        form.getNumberoffloors(), form.getPlace(), form.getWorkcontent(), form.getNumberofpeople());
    URI uri = uriBuilder.path("schedules/" + se.getScheduleid()).build().toUri();
    var srm = new ScheduleResponseMassage();
    srm.setMessage("作業予定を登録しました");
    return ResponseEntity.created(uri).body(srm);
  }

  @PatchMapping("/schedules/{id}")
  private ResponseEntity<ScheduleResponseMassage> updateSchedule(
      @PathVariable("id") Long scheduleId, @RequestBody @Validated ScheduleForm form) {
    scheduleService.update(scheduleId, form.getUserid(), form.getWorkingdate(),
        form.getNumberoffloors(), form.getPlace(), form.getWorkcontent(), form.getNumberofpeople());
    var srm = new ScheduleResponseMassage();
    srm.setMessage("作業予定を更新しました");
    return ResponseEntity.ok(srm);
  }

  @DeleteMapping("/schedules/{id}")
  private ResponseEntity<ScheduleResponseMassage> deleteSchedule(
      @PathVariable("id") Long ScheduleId) {
    scheduleService.delete(ScheduleId);
    return ResponseEntity.noContent().build();
  }

}
