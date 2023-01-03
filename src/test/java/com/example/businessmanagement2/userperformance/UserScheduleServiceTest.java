package com.example.businessmanagement2.userperformance;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import com.example.businessmanagement2.repository.performance.PerformanceEntity;
import com.example.businessmanagement2.repository.userperformance.UserPerformance;
import com.example.businessmanagement2.repository.userperformance.UserPerformanceRepository;
import com.example.businessmanagement2.service.user.UserEntityNotFoundException;
import com.example.businessmanagement2.service.userperformance.UserPerformanceServiceImpl;
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
public class UserScheduleServiceTest {

  @InjectMocks
  private UserPerformanceServiceImpl userPerformanceServiceImpl;

  @Mock
  private UserPerformanceRepository userPerformanceRepository;

  @Test
  public void すべてのユーザーと作業実績を全件取得してそのまま返すこと() {
    List<UserPerformance> UserPerformance = new ArrayList<>();
    UserPerformance.add(new UserPerformance(1L, "○○○会社", "瀬川",
        List.of(new PerformanceEntity(1L, 1, LocalDate.of(2022, 12, 6), "4階トイレ", "墨出し", 3))));
    UserPerformance.add(new UserPerformance(2L, "△△△会社", "瀬川２",
        List.of(new PerformanceEntity(2L, 2, LocalDate.of(2022, 12, 7), "3階和室", "配線", 5))));
    UserPerformance.add(new UserPerformance(2L, "△△△会社", "瀬川２",
        List.of(
            new PerformanceEntity(3L, 2, LocalDate.of(2022, 12, 10), "3階トイレ２", "BOX取り付け", 2))));
    UserPerformance.add(new UserPerformance(3L, "xxx会社", "瀬川２",
        List.of(new PerformanceEntity(4L, 3, LocalDate.of(2022, 12, 9), "4階洋室", "配管", 4))));

    when(userPerformanceRepository.findUserPerformanceList()).thenReturn(UserPerformance);
    List<UserPerformance> actual = userPerformanceServiceImpl.findUserPerformanceList();
    assertThat(actual).hasSize(4);
  }

  @Test
  public void 存在するユーザーと作業実績IDを指定したとき正常にユーザーと作業実績が返されること() {
    doReturn(Optional.of(new UserPerformance(2L, "△△△会社", "瀬川２",
        List.of(new PerformanceEntity(2L, 2, LocalDate.of(2022, 12, 7), "3階和室", "配線", 5)))))
        .when(userPerformanceRepository).findById(2L);
    UserPerformance actual = userPerformanceServiceImpl.findById(2L);
    assertThat(actual).isEqualTo(
        new UserPerformance(2L, "△△△会社", "瀬川２",
            List.of(
                new PerformanceEntity(2L, 2, LocalDate.of(2022, 12, 7), "3階和室", "配線", 5))));
  }

  @Test
  public void 検索時に該当するIDのユーザーと作業実績がいないときUserEntityNotFoundExceptionとなること() {
    doReturn(Optional.empty()).when(userPerformanceRepository).findById(99L);
    assertThatThrownBy(() -> {
      userPerformanceServiceImpl.findById(99L);
    }).isInstanceOf(UserEntityNotFoundException.class);
  }

}

