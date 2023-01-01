package com.example.businessmanagement2.schedule;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.businessmanagement2.repository.schedule.ScheduleEntity;
import com.example.businessmanagement2.repository.schedule.ScheduleRepository;
import com.example.businessmanagement2.service.schedule.ScheduleEntityNotFoundException;
import com.example.businessmanagement2.service.schedule.ScheduleServiceImpl;
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
public class ScheduleServiceTest {

  @InjectMocks
  private ScheduleServiceImpl scheduleServiceImpl;

  @Mock
  private ScheduleRepository scheduleRepository;

  @Test
  public void すべての作業予定を全件取得してそのまま返すこと() {
    List<ScheduleEntity> ScheduleEntity = new ArrayList<>();
    ScheduleEntity.add(new ScheduleEntity(1L, 1, LocalDate.of(2022, 12, 6), "4階トイレ", "墨出し", 3));
    ScheduleEntity.add(new ScheduleEntity(2L, 2, LocalDate.of(2022, 12, 7), "3階和室", "配線", 5));
    ScheduleEntity.add(
        new ScheduleEntity(3L, 2, LocalDate.of(2022, 12, 10), "3階トイレ２", "BOX取り付け", 2));
    ScheduleEntity.add(new ScheduleEntity(4L, 3, LocalDate.of(2022, 12, 9), "4階洋室", "配管", 4));

    when(scheduleRepository.findScheduleList()).thenReturn(ScheduleEntity);
    List<ScheduleEntity> actual = scheduleServiceImpl.findScheduleList();
    assertThat(actual).hasSize(4);
  }

  @Test
  public void 存在する作業予定のIDを指定したとき正常に作業予定が返されること() {
    doReturn(Optional.of(
        new ScheduleEntity(2L, 2, LocalDate.of(2022, 12, 7), "3階和室", "配線", 5))).when(
        scheduleRepository).findById(2L);
    ScheduleEntity actual = scheduleServiceImpl.findById(2L);
    assertThat(actual).isEqualTo(
        new ScheduleEntity(2L, 2, LocalDate.of(2022, 12, 7), "3階和室", "配線", 5));
  }

  @Test
  public void 検索時に該当するIDの作業予定がいないときScheduleEntityNotFoundExceptionとなること() {
    doReturn(Optional.empty()).when(scheduleRepository).findById(99L);
    assertThatThrownBy(() -> {
      scheduleServiceImpl.findById(99L);
    }).isInstanceOf(ScheduleEntityNotFoundException.class);
  }

  @Test
  public void 作業予定を新規登録できること() {
    ScheduleEntity newSe = new ScheduleEntity(null, 3, LocalDate.of(2022, 12, 7), "3階和室", "配線",
        6);
    scheduleServiceImpl.create(newSe.getUserId(), newSe.getWorkingDate(), newSe.getPlace(),
        newSe.getWorkContent(), newSe.getNumberOfPeople());
    verify(scheduleRepository).create(newSe);
  }

  @Test
  public void 指定したIDの作業予定を更新できること() {
    ScheduleEntity se = new ScheduleEntity(1L, 1, LocalDate.of(2022, 12, 6), "4階トイレ", "墨出し", 3);
    ScheduleEntity newSe = new ScheduleEntity(1L, 1, LocalDate.of(2022, 12, 11), "5階和室", "配線",
        6);
    given(scheduleRepository.findById(se.getScheduleId())).willReturn(Optional.of(se));
    scheduleServiceImpl.update(se.getScheduleId(), newSe.getUserId(), newSe.getWorkingDate(),
        newSe.getPlace(), newSe.getWorkContent(), newSe.getNumberOfPeople());
    verify(scheduleRepository).update(newSe);
  }

  @Test
  public void 更新時に該当するIDの作業予定がいないときScheduleEntityNotFoundExceptionとなること() {
    ScheduleEntity se = new ScheduleEntity(1L, 1, LocalDate.of(2022, 12, 7), "4階トイレ", "墨出し", 3);
    ScheduleEntity newSe = new ScheduleEntity(1L, 1, LocalDate.of(2022, 12, 11), "5階和室", "配線",
        6);
    given(scheduleRepository.findById(se.getScheduleId())).willReturn(Optional.of(se));
    scheduleServiceImpl.update(se.getScheduleId(), newSe.getUserId(), newSe.getWorkingDate(),
        newSe.getPlace(), newSe.getWorkContent(), newSe.getNumberOfPeople());
    assertThatThrownBy(() -> {
      scheduleServiceImpl.findById(99L);
    }).isInstanceOf(ScheduleEntityNotFoundException.class);
  }

  @Test
  public void 指定した作業予定を1件削除できること() {
    ScheduleEntity se = new ScheduleEntity(1L, 1, LocalDate.of(2022, 12, 7), "4階トイレ", "墨出し", 3);
    given(scheduleRepository.findById(se.getScheduleId())).willReturn(Optional.of(se));
    scheduleServiceImpl.delete(se.getScheduleId());
    verify(scheduleRepository).delete(se.getScheduleId());
  }

  @Test
  public void 削除時に該当するIDの作業予定がいないときScheduleEntityNotFoundExceptionとなること() {
    ScheduleEntity se = new ScheduleEntity(1L, 1, LocalDate.of(2022, 12, 7), "4階トイレ", "墨出し", 3);
    given(scheduleRepository.findById(se.getScheduleId())).willReturn(Optional.of(se));
    scheduleServiceImpl.delete(se.getScheduleId());
    assertThatThrownBy(() -> {
      scheduleServiceImpl.findById(99L);
    }).isInstanceOf(ScheduleEntityNotFoundException.class);
  }
}

