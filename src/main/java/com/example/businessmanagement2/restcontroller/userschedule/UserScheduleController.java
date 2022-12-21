package com.example.businessmanagement2.restcontroller.userschedule;


import com.example.businessmanagement2.repository.userschedule.UserSchedule;
import com.example.businessmanagement2.service.userschedule.UserScheduleService;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class UserScheduleController {

  private final UserScheduleService userScheduleService;

  private static UserScheduleDTO toUserScheduleDTO(UserSchedule userSchedule) {
    var UserScheduleDTO = new UserScheduleDTO(userSchedule.getUserid(), userSchedule.getCompanyname(), userSchedule.getUsername(),
        userSchedule.getScheduleEntitys());
    UserScheduleDTO.setUserid(userSchedule.getUserid());
    UserScheduleDTO.setCompanyname(userSchedule.getCompanyname());
    UserScheduleDTO.setUsername(userSchedule.getUsername());
    UserScheduleDTO.setScheduleEntitys(userSchedule.getScheduleEntitys());
    return UserScheduleDTO;
  }

  @GetMapping("/userschedules/{id}")
  private ResponseEntity<UserScheduleDTO> showUserSchedule(@PathVariable("id") Long userid) {
    var entity = userScheduleService.findById(userid);
    var dto = toUserScheduleDTO(entity);
    return ResponseEntity.ok(dto);
  }

  @GetMapping("/userschedules")
  private ResponseEntity<UserScheduleListDTO> findUserScheduleList() {
    var entityList = userScheduleService.findUserScheduleList();
    var dtoList = entityList
        .stream()
        .map(UserScheduleController::toUserScheduleDTO)
        .collect(Collectors.toList());
    var dto = new UserScheduleListDTO();
    dto.setResults(dtoList);
    return ResponseEntity.ok(dto);
  }

}
