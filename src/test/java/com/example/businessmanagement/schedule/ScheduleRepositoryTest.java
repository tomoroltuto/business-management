package com.example.businessmanagement.schedule;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.businessmanagement.repository.schedule.ScheduleEntity;
import com.example.businessmanagement.repository.schedule.ScheduleRepository;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

@DBRider
@MybatisTest
@DataSet(value = "schedule/datasets/schedules.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ScheduleRepositoryTest {

  @Autowired
  ScheduleRepository scheduleRepository;

  @Test
  @Transactional
  void すべての作業予定が取得できること() {
    List<ScheduleEntity> schedules = scheduleRepository.findScheduleList();
    assertThat(schedules).hasSize(4)
        .contains(new ScheduleEntity(1L, 1, LocalDate.of(2022, 12, 6), "4階トイレ", "墨出し", 3),
            new ScheduleEntity(2L, 2, LocalDate.of(2022, 12, 7), "3階和室", "配線", 5),
            new ScheduleEntity(3L, 2, LocalDate.of(2022, 12, 10), "3階トイレ２", "BOX取り付け", 2),
            new ScheduleEntity(4L, 3, LocalDate.of(2022, 12, 9), "4階洋室", "配管", 4));
  }

  @Test
  @DataSet(cleanBefore = true)
  @Transactional
  void 作業予定が登録されてないときに空のリストが返ること() {
    List<ScheduleEntity> schedules = scheduleRepository.findScheduleList();
    assertThat(schedules).isEmpty();
  }

  @Test
  @Transactional
  void 存在する作業予定のIDを指定してユーザーが取得できること() {
    ScheduleEntity schedule = scheduleRepository.findById(2L).orElseThrow();
    assertThat(schedule).isEqualTo(
        new ScheduleEntity(2L, 2, LocalDate.of(2022, 12, 7), "3階和室", "配線", 5));
  }

  @Test
  @ExpectedDataSet(value = "schedule/datasets/createschedules.yml", ignoreCols = "schedule_id")
  @Transactional
  public void 作業予定を新規登録できること() {
    ScheduleEntity se = new ScheduleEntity(null, 3, LocalDate.of(2022, 12, 30), "5階洋室", "墨出し",
        5);
    scheduleRepository.create(se);
    List<ScheduleEntity> actual = scheduleRepository.findScheduleList();
    assertThat(actual).hasSize(5);
  }

  @Test
  @ExpectedDataSet(value = "schedule/datasets/updateschedules.yml")
  @Transactional
  public void キーに紐づく1件の更新が出来ること() {
    scheduleRepository.update(
        new ScheduleEntity(1L, 1, LocalDate.of(2023, 2, 2), "6階和室", "配線", 7));
    Optional<ScheduleEntity> schedule = scheduleRepository.findById(1L);
    assertThat(schedule).hasValue(
        new ScheduleEntity(1L, 1, LocalDate.of(2023, 2, 2), "6階和室", "配線", 7));
  }

  @Test
  @ExpectedDataSet(value = "schedule/datasets/deleteschedules.yml")
  @Transactional
  public void 指定した作業予定を1件削除できること() {
    scheduleRepository.delete(1L);
    List<ScheduleEntity> actual = scheduleRepository.findScheduleList();
    assertThat(actual).hasSize(3);
  }

}
