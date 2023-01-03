package com.example.businessmanagement2.performance;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.businessmanagement2.repository.performance.PerformanceEntity;
import com.example.businessmanagement2.repository.performance.PerformanceRepository;
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
@DataSet(value = "performance/datasets/performances.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PerformanceRepositoryTest {

  @Autowired
  PerformanceRepository performanceRepository;

  @Test
  @Transactional
  void すべての作業実績が取得できること() {
    List<PerformanceEntity> performances = performanceRepository.findPerformanceList();
    assertThat(performances).hasSize(4)
        .contains(new PerformanceEntity(1L, 1, LocalDate.of(2022, 12, 6), "4階トイレ", "墨出し", 3),
            new PerformanceEntity(2L, 2, LocalDate.of(2022, 12, 7), "3階和室", "配線", 5),
            new PerformanceEntity(3L, 2, LocalDate.of(2022, 12, 10), "3階トイレ２", "BOX取り付け", 2),
            new PerformanceEntity(4L, 3, LocalDate.of(2022, 12, 9), "4階洋室", "配管", 4));
  }

  @Test
  @DataSet(cleanBefore = true)
  @Transactional
  void 作業実績が登録されてないときに空のリストが返ること() {
    List<PerformanceEntity> performances = performanceRepository.findPerformanceList();
    assertThat(performances).isEmpty();
  }

  @Test
  @Transactional
  void 存在する作業実績のIDを指定してユーザーが取得できること() {
    PerformanceEntity performance = performanceRepository.findById(2L).orElseThrow();
    assertThat(performance).isEqualTo(
        new PerformanceEntity(2L, 2, LocalDate.of(2022, 12, 7), "3階和室", "配線", 5));
  }

  @Test
  @ExpectedDataSet(value = "performance/datasets/createperformances.yml", ignoreCols = "performance_id")
  @Transactional
  public void 作業実績を新規登録できること() {
    PerformanceEntity pe = new PerformanceEntity(null, 3, LocalDate.of(2022, 12, 30), "5階洋室",
        "墨出し", 5);
    performanceRepository.create(pe);
    List<PerformanceEntity> actual = performanceRepository.findPerformanceList();
    assertThat(actual).hasSize(5);
  }

  @Test
  @ExpectedDataSet(value = "performance/datasets/updateperformances.yml")
  @Transactional
  public void キーに紐づく1件の更新が出来ること() {
    performanceRepository.update(
        new PerformanceEntity(1L, 1, LocalDate.of(2023, 2, 2), "6階和室", "配線", 7));
    Optional<PerformanceEntity> performance = performanceRepository.findById(1L);
    assertThat(performance).hasValue(
        new PerformanceEntity(1L, 1, LocalDate.of(2023, 2, 2), "6階和室", "配線", 7));
  }

  @Test
  @ExpectedDataSet(value = "performance/datasets/deleteperformances.yml")
  @Transactional
  public void 指定した作業実績を1件削除できること() {
    performanceRepository.delete(1L);
    List<PerformanceEntity> performance = performanceRepository.findPerformanceList();
    assertThat(performance).hasSize(3);
  }

}
