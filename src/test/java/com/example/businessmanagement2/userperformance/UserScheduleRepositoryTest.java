package com.example.businessmanagement2.userperformance;


import static org.assertj.core.api.Assertions.assertThat;

import com.example.businessmanagement2.repository.performance.PerformanceEntity;
import com.example.businessmanagement2.repository.userperformance.UserPerformance;
import com.example.businessmanagement2.repository.userperformance.UserPerformanceRepository;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

@DBRider
@MybatisTest
@DataSet(value = "userperformance/datasets/userperformances.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserScheduleRepositoryTest {

  @Autowired
  UserPerformanceRepository userPerformanceRepository;

  @Test
  @Transactional
  void すべてのユーザーと作業実績が取得できること() {
    List<UserPerformance> userPerformances = userPerformanceRepository.findUserPerformanceList();
    assertThat(userPerformances).hasSize(3).contains((new UserPerformance(1L, "○○○会社", "瀬川",
            List.of(new PerformanceEntity(1L, 1, LocalDate.of(2022, 12, 6), "4階トイレ", "墨出し", 3)))),
        (new UserPerformance(2L, "△△△会社", "瀬川2",
            List.of(new PerformanceEntity(2L, 2, LocalDate.of(2022, 12, 7), "3階和室", "配線", 5),
                (new PerformanceEntity(3L, 2, LocalDate.of(2022, 12, 10), "3階トイレ２", "BOX取り付け",
                    2))))), (new UserPerformance(3L, "xxx会社", "瀬川3", List.of(
            new PerformanceEntity(4L, 3, LocalDate.of(2022, 12, 9), "4階洋室", "配管", 4)))));
  }

  @Test
  @DataSet(cleanBefore = true)
  @Transactional
  void ユーザーと作業実績が登録されてないときに空のリストが返ること() {
    List<UserPerformance> userPerformances = userPerformanceRepository.findUserPerformanceList();
    assertThat(userPerformances).isEmpty();
  }

  @Test
  @Transactional
  void 存在するユーザーと作業実績のIDを指定してユーザーが取得できること() {
    UserPerformance userPerformance = userPerformanceRepository.findById(2L).orElseThrow();
    assertThat(userPerformance).isEqualTo(new UserPerformance(2L, "△△△会社", "瀬川2",
        List.of(new PerformanceEntity(2L, 2, LocalDate.of(2022, 12, 7), "3階和室", "配線", 5),
            (new PerformanceEntity(3L, 2, LocalDate.of(2022, 12, 10), "3階トイレ２", "BOX取り付け",
                2)))));
  }
}
