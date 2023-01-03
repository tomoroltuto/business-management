package com.example.businessmanagement.performance;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.businessmanagement.repository.performance.PerformanceEntity;
import com.example.businessmanagement.repository.performance.PerformanceRepository;
import com.example.businessmanagement.service.performance.PerformanceEntityNotFoundException;
import com.example.businessmanagement.service.performance.PerformanceServiceImpl;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PerformanceServiceTest {

  @InjectMocks
  private PerformanceServiceImpl performanceServiceImpl;

  @Mock
  private PerformanceRepository performanceRepository;

  @Test
  public void すべての作業実績を全件取得してそのまま返すこと() {
    List<PerformanceEntity> PerformanceEntity = new ArrayList<>();
    PerformanceEntity.add(
        new PerformanceEntity(1L, 1, LocalDate.of(2022, 12, 6), "4階トイレ", "墨出し", 3));
    PerformanceEntity.add(
        new PerformanceEntity(2L, 2, LocalDate.of(2022, 12, 7), "3階和室", "配線", 5));
    PerformanceEntity.add(
        new PerformanceEntity(3L, 2, LocalDate.of(2022, 12, 10), "3階トイレ２", "BOX取り付け", 2));
    PerformanceEntity.add(
        new PerformanceEntity(4L, 3, LocalDate.of(2022, 12, 9), "4階洋室", "配管", 4));

    when(performanceRepository.findPerformanceList()).thenReturn(PerformanceEntity);
    List<PerformanceEntity> actual = performanceServiceImpl.findPerformanceList();
    assertThat(actual).hasSize(4);
  }

  @Test
  public void 存在する作業実績IDを指定したとき正常に作業予定が返されること() {
    doReturn(
        Optional.of(new PerformanceEntity(2L, 2, LocalDate.of(2022, 12, 7), "3階和室", "配線", 5)))
        .when(performanceRepository).findById(2L);
    PerformanceEntity actual = performanceServiceImpl.findById(2L);
    assertThat(actual).isEqualTo(
        new PerformanceEntity(2L, 2, LocalDate.of(2022, 12, 7), "3階和室", "配線", 5));
  }

  @Test
  public void 検索時に該当するIDの作業実績がいないときPerformanceEntityNotFoundExceptionとなること() {
    doReturn(Optional.empty()).when(performanceRepository).findById(99L);
    assertThatThrownBy(() -> {
      performanceServiceImpl.findById(99L);
    }).isInstanceOf(PerformanceEntityNotFoundException.class);
  }

  @Test
  public void 作業実績を新規登録できること() {
    PerformanceEntity newPe = new PerformanceEntity(null, 3, LocalDate.of(2022, 12, 7), "3階和室",
        "配線",
        6);
    performanceServiceImpl.create(newPe.getUserId(), newPe.getWorkingDate(), newPe.getPlace(),
        newPe.getWorkContent(), newPe.getNumberOfPeople());
    verify(performanceRepository).create(newPe);
  }

  @Test
  public void 指定したIDの作業実績を更新できること() {
    PerformanceEntity pe = new PerformanceEntity(1L, 1, LocalDate.of(2022, 12, 6), "4階トイレ",
        "墨出し", 3);
    PerformanceEntity newPe = new PerformanceEntity(1L, 1, LocalDate.of(2022, 12, 11), "5階和室",
        "配線",
        6);
    given(performanceRepository.findById(pe.getPerformanceId())).willReturn(Optional.of(pe));
    performanceServiceImpl.update(pe.getPerformanceId(), newPe.getUserId(), newPe.getWorkingDate(),
        newPe.getPlace(), newPe.getWorkContent(), newPe.getNumberOfPeople());
    verify(performanceRepository).update(newPe);
  }

  @Test
  public void 更新時に該当するIDの作業実績がいないときPerformanceEntityNotFoundExceptionとなること() {
    PerformanceEntity pe = new PerformanceEntity(1L, 1, LocalDate.of(2022, 12, 6), "4階トイレ",
        "墨出し", 3);
    PerformanceEntity newPe = new PerformanceEntity(1L, 1, LocalDate.of(2022, 12, 11), "5階和室",
        "配線",
        6);
    given(performanceRepository.findById(pe.getPerformanceId())).willReturn(Optional.of(pe));
    performanceServiceImpl.update(pe.getPerformanceId(), newPe.getUserId(), newPe.getWorkingDate(),
        newPe.getPlace(), newPe.getWorkContent(), newPe.getNumberOfPeople());
    assertThatThrownBy(() -> {
      performanceServiceImpl.findById(99L);
    }).isInstanceOf(PerformanceEntityNotFoundException.class);
  }

  @Test
  public void 指定した作業実績を1件削除できること() {
    PerformanceEntity pe = new PerformanceEntity(1L, 1, LocalDate.of(2022, 12, 7), "4階トイレ",
        "墨出し", 3);
    given(performanceRepository.findById(pe.getPerformanceId())).willReturn(Optional.of(pe));
    performanceServiceImpl.delete(pe.getPerformanceId());
    verify(performanceRepository).delete(pe.getPerformanceId());
  }

  @Test
  public void 削除時に該当するIDの作業実績がいないときPerformanceEntityNotFoundExceptionとなること() {
    PerformanceEntity pe = new PerformanceEntity(1L, 1, LocalDate.of(2022, 12, 7), "4階トイレ",
        "墨出し", 3);
    given(performanceRepository.findById(pe.getPerformanceId())).willReturn(Optional.of(pe));
    performanceServiceImpl.delete(pe.getPerformanceId());
    assertThatThrownBy(() -> {
      performanceServiceImpl.findById(99L);
    }).isInstanceOf(PerformanceEntityNotFoundException.class);
  }
}
