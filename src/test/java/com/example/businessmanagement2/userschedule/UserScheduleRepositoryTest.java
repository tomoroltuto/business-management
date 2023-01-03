package com.example.businessmanagement2.userschedule;


import static org.assertj.core.api.Assertions.assertThat;

import com.example.businessmanagement2.repository.schedule.ScheduleEntity;
import com.example.businessmanagement2.repository.userschedule.UserSchedule;
import com.example.businessmanagement2.repository.userschedule.UserScheduleRepository;
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
@DataSet(value = "userschedule/datasets/userschedules.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserScheduleRepositoryTest {

  @Autowired
  UserScheduleRepository userScheduleRepository;

  @Test
  @Transactional
  void すべてのユーザーと作業予定が取得できること() {
    List<UserSchedule> userSchedules = userScheduleRepository.findUserScheduleList();
    assertThat(userSchedules).hasSize(3)
        .contains(
            (new UserSchedule(1L, "○○○会社", "瀬川", List.of(new ScheduleEntity(1L, 1, LocalDate.of(2022, 12, 6), "4階トイレ", "墨出し", 3)))),
            (new UserSchedule(2L, "△△△会社", "瀬川2", List.of(new ScheduleEntity(2L, 2, LocalDate.of(2022, 12, 7), "3階和室", "配線", 5), (new ScheduleEntity(3L, 2, LocalDate.of(2022, 12, 10), "3階トイレ２", "BOX取り付け", 2))))),
            (new UserSchedule(3L, "xxx会社", "瀬川3", List.of(new ScheduleEntity(4L, 3, LocalDate.of(2022, 12, 9), "4階洋室", "配管", 4)))));
  }

  @Test
  @DataSet(cleanBefore = true)
  @Transactional
  void ユーザーと作業予定が登録されてないときに空のリストが返ること() {
    List<UserSchedule> userSchedules = userScheduleRepository.findUserScheduleList();
    assertThat(userSchedules).isEmpty();
  }

  @Test
  @Transactional
  void 存在するユーザーと作業予定のIDを指定してユーザーが取得できること() {
    UserSchedule userSchedule = userScheduleRepository.findById(2L).orElseThrow();
    assertThat(userSchedule).isEqualTo
            (new UserSchedule(2L, "△△△会社", "瀬川2", List.of(new ScheduleEntity(2L, 2, LocalDate.of(2022, 12, 7), "3階和室", "配線", 5), (new ScheduleEntity(3L, 2, LocalDate.of(2022, 12, 10), "3階トイレ２", "BOX取り付け", 2)))));
  }
}

