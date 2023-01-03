package com.example.businessmanagement.repository.userperformance;

import com.example.businessmanagement.repository.performance.PerformanceEntity;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserPerformance {

  Long userId;

  String companyName;

  String userName;

  List<PerformanceEntity> performanceEntities;


}

