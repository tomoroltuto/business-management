package com.example.businessmanagement2.controller.userperformance;


import com.example.businessmanagement2.repository.userperformance.UserPerformance;
import com.example.businessmanagement2.repository.userschedule.UserSchedule;
import com.example.businessmanagement2.service.userperformance.UserPerformanceService;
import com.example.businessmanagement2.service.userschedule.UserScheduleService;
import java.util.ArrayList;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class UserPerformanceController {

  private final UserPerformanceService userPerformanceService;

  private static UserPerformanceDTO toUserScheduleDTO(UserPerformance userPerformance) {
    var UserPerformanceDTO = new UserPerformanceDTO(userPerformance.getUserId(),
        userPerformance.getCompanyName(), userPerformance.getUserName(),
        userPerformance.getPerformanceEntities());
    return UserPerformanceDTO;
  }

  @GetMapping("/userperformances/{id}")
  private ResponseEntity<UserPerformanceDTO> showUserPerformance(@PathVariable("id") Long userid) {
    var entity = userPerformanceService.findById(userid);
    var dto = toUserScheduleDTO(entity);
    return ResponseEntity.ok(dto);
  }

  @GetMapping("/userperformances")
  private ResponseEntity<UserPerformanceListDTO> findUserPerformanceList() {
    var entityList = userPerformanceService.findUserPerformanceList();
    var dtoList = entityList.stream().map(UserPerformanceController::toUserScheduleDTO)
        .collect(Collectors.toList());
    var dto = new UserPerformanceListDTO(new ArrayList<>(dtoList));
    return ResponseEntity.ok(dto);
  }
}
