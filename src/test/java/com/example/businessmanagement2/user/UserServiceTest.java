package com.example.businessmanagement2.user;


import com.example.businessmanagement2.repository.user.UserEntity;
import com.example.businessmanagement2.repository.user.UserRepository;
import com.example.businessmanagement2.service.user.UserEntityNotFoundException;
import com.example.businessmanagement2.service.user.UserServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;


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
  public void 存在するユーザのIDを指定したとき正常にユーザーが返されること(){
    doReturn(Optional.of(new UserEntity(1L, "○○○会社", "瀬川"))).when(userRepository).findById(1);
    UserEntity actual = userServiceImpl.findById(1L);
    assertThat(actual).isEqualTo(new UserEntity(1L,"○○○会社","瀬川"));
  }


  @Test
  public void 検索時に該当するIDのユーザーがいないときUserEntityNotFoundExceptionとなること(){
    doReturn(Optional.empty()).when(userRepository).findById(3);
    assertThatThrownBy(() -> {
      userServiceImpl.findById(3L);
    }).isInstanceOf(UserEntityNotFoundException.class);
  }
  @Test
  public void データーを新規登録できること() {
    UserEntity newUe = new UserEntity (null,"xxx会社","瀬川3");
    userServiceImpl.create(newUe.getCompanyname(), newUe.getUsername());
    verify(userRepository).create(newUe);
  }

  @Test
  void 指定したIDのデーターを更新できること() {

    userServiceImpl.update(1L, "xxx会社", "瀬川2");

    verify(userRepository).update(new UserEntity(1L, "xx会社", "瀬川2"));
  }




}
