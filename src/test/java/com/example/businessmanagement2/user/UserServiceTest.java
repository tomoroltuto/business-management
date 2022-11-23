package com.example.businessmanagement2.user;


import com.example.businessmanagement2.repository.user.UserEntity;
import com.example.businessmanagement2.repository.user.UserRepository;
import com.example.businessmanagement2.restcontroller.user.UserForm;
import com.example.businessmanagement2.restcontroller.user.UserResponseMessage;
import com.example.businessmanagement2.service.user.UserEntityNotFoundException;
import com.example.businessmanagement2.service.user.UserServiceImpl;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @InjectMocks
  UserServiceImpl userServiceImpl;

  @Mock
  UserRepository userRepository;

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

}
