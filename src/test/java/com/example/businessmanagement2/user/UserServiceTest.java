package com.example.businessmanagement2.user;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.businessmanagement2.repository.user.UserEntity;
import com.example.businessmanagement2.repository.user.UserRepository;
import com.example.businessmanagement2.service.user.UserEntityNotFoundException;
import com.example.businessmanagement2.service.user.UserServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @InjectMocks
  private UserServiceImpl userServiceImpl;

  @Mock
  private UserRepository userRepository;

  @Test
  public void すべてのユーザーを全件取得してそのまま返すこと() {
    List<UserEntity> UserEntity = new ArrayList<>();
    UserEntity.add(new UserEntity(1L, "○○○会社", "瀬川"));
    UserEntity.add(new UserEntity(2L, "△△△会社", "瀬川2"));
    when(userRepository.findUserList()).thenReturn(UserEntity);
    List<UserEntity> actual = userServiceImpl.findUserList();
    assertThat(actual).hasSize(2);
  }

  @Test
  public void 存在するユーザのIDを指定したとき正常にユーザーが返されること() {
    doReturn(Optional.of(new UserEntity(1L, "○○○会社", "瀬川")))
        .when(userRepository).findById(1);
    UserEntity actual = userServiceImpl.findById(1L);
    assertThat(actual).isEqualTo(new UserEntity(1L, "○○○会社", "瀬川"));
  }


  @Test
  public void 検索時に該当するIDのユーザーがいないときUserEntityNotFoundExceptionとなること() {
    doReturn(Optional.empty()).when(userRepository).findById(3);
    assertThatThrownBy(() -> {
      userServiceImpl.findById(3L);
    }).isInstanceOf(UserEntityNotFoundException.class);
  }

  @Test
  public void ユーザーを新規登録できること() {
    UserEntity newUe = new UserEntity(null, "xxx会社", "瀬川3");
    userServiceImpl.create(newUe.getCompanyName(), newUe.getUserName());
    verify(userRepository).create(newUe);
  }

  @Test
  public void 指定したIDのユーザーを更新できること() {
    UserEntity ue = new UserEntity(1L, "○○○会社", "瀬川");
    UserEntity newUe = new UserEntity(1L, "xxx会社", "瀬川3");
    given(userRepository.findById(ue.getUserid())).willReturn(Optional.of(ue));
    userServiceImpl.update(ue.getUserid(), newUe.getCompanyName(), newUe.getUserName());
    verify(userRepository).update(newUe);
  }

  @Test
  public void 更新時に該当するIDのユーザーがいないときUserEntityNotFoundExceptionとなること() {
    UserEntity ue = new UserEntity(1L, "○○○会社", "瀬川");
    UserEntity newUe = new UserEntity(1L, "xxx会社", "瀬川3");
    given(userRepository.findById(ue.getUserid())).willReturn(Optional.of(ue));
    userServiceImpl.update(ue.getUserid(), newUe.getCompanyName(), newUe.getUserName());
    assertThatThrownBy(() -> {
      userServiceImpl.findById(99L);
    }).isInstanceOf(UserEntityNotFoundException.class);
  }

  @Test
  public void 指定したデーターを1件削除できること() {
    UserEntity ue = new UserEntity(1L, "○○○会社", "瀬川");
    given(userRepository.findById(ue.getUserid())).willReturn(Optional.of(ue));
    userServiceImpl.delete(ue.getUserid());
    verify(userRepository).delete(ue.getUserid());
  }

  @Test
  public void 削除時に該当するIDのユーザーがいないときUserEntityNotFoundExceptionとなること() {
    UserEntity ue = new UserEntity(1L, "○○○会社", "瀬川");
    given(userRepository.findById(ue.getUserid())).willReturn(Optional.of(ue));
    userServiceImpl.delete(ue.getUserid());
    assertThatThrownBy(() -> {
      userServiceImpl.findById(99L);
    }).isInstanceOf(UserEntityNotFoundException.class);
  }
}
