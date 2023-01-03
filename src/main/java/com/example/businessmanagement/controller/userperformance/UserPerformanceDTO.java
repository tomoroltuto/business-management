package com.example.businessmanagement.controller.userperformance;


import com.example.businessmanagement.repository.performance.PerformanceEntity;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class UserPerformanceDTO {

  Long userId;

  String companyName;

  String userName;

  List<PerformanceEntity> performanceEntities;

}
