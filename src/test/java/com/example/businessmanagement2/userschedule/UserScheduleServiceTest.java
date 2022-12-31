package com.example.businessmanagement2.userschedule;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import com.example.businessmanagement2.repository.schedule.ScheduleEntity;
import com.example.businessmanagement2.repository.userschedule.UserSchedule;
import com.example.businessmanagement2.repository.userschedule.UserScheduleRepository;
import com.example.businessmanagement2.service.user.UserEntityNotFoundException;
import com.example.businessmanagement2.service.userschedule.UserScheduleServiceImpl;
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
  private UserScheduleServiceImpl userScheduleServiceImpl;

  @Mock
  private UserScheduleRepository userScheduleRepository;

  @Test
  public void すべてのユーザーと作業予定を全件取得してそのまま返すこと() {
    List<UserSchedule> UserSchedule = new ArrayList<>();
    UserSchedule.add(new UserSchedule(1L, "○○○会社", "瀬川",
        List.of(new ScheduleEntity(1L, 1, LocalDate.of(2022, 12, 6), "4階トイレ", "墨出し", 3))));
    UserSchedule.add(new UserSchedule(2L, "△△△会社", "瀬川２",
        List.of(new ScheduleEntity(2L, 2, LocalDate.of(2022, 12, 7), "3階和室", "配線", 5))));
    UserSchedule.add(new UserSchedule(2L, "△△△会社", "瀬川２",
        List.of(new ScheduleEntity(3L, 2, LocalDate.of(2022, 12, 10), "3階トイレ２", "BOX取り付け", 2))));
    UserSchedule.add(new UserSchedule(3L, "xxx会社", "瀬川２",
        List.of(new ScheduleEntity(4L, 3, LocalDate.of(2022, 12, 9), "4階洋室", "配管", 4))));

    when(userScheduleRepository.findUserScheduleList()).thenReturn(UserSchedule);
    List<UserSchedule> actual = userScheduleServiceImpl.findUserScheduleList();
    assertThat(actual).hasSize(4);
  }

  @Test
  public void 存在するユーザーと作業予定IDを指定したとき正常にユーザーと作業予定が返されること() {
    doReturn(Optional.of(new UserSchedule(2L, "△△△会社", "瀬川２",
        List.of(new ScheduleEntity(2L, 2, LocalDate.of(2022, 12, 7), "3階和室", "配線", 5)))))
        .when(userScheduleRepository).findById(2L);
    UserSchedule actual = userScheduleServiceImpl.findById(2L);
    assertThat(actual).isEqualTo(
        new UserSchedule(2L, "△△△会社", "瀬川２",
            List.of(new ScheduleEntity(2L, 2, LocalDate.of(2022, 12, 7), "3階和室", "配線", 5))));
  }

  @Test
  public void 検索時に該当するIDのユーザーと作業予定がいないときUserEntityNotFoundExceptionとなること() {
    doReturn(Optional.empty()).when(userScheduleRepository).findById(99L);
    assertThatThrownBy(() -> {
      userScheduleServiceImpl.findById(99L);
    }).isInstanceOf(UserEntityNotFoundException.class);
  }

}
