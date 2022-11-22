package com.example.businessmanagement2.user;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;


import com.example.businessmanagement2.repository.user.UserEntity;
import com.example.businessmanagement2.repository.user.UserRepository;
import com.example.businessmanagement2.service.user.UserServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @InjectMocks
  UserServiceImpl userServiceImpl;

  @Mock
  UserRepository userRepository;

  @Test
  public void 存在するユーザのIDを指定したとき正常にユーザーが返されること(){
    doReturn(Optional.of(new UserEntity(1L, "○○○会社", "瀬川"))).when(userRepository).findById(1);
    UserEntity actual = userServiceImpl.findById(1L);
    assertThat(actual).isEqualTo(new UserEntity(1L,"○○○会社","瀬川"));
  }
//  @Test
////  public void 該当するIDのUserインスタンスが取得できること(){
////    when(userRepository.select(1)).thenReturn(Optional.of( new UserRecord(null,"○○○会社", "瀬川")));
////    UserEntity actual = userService.select(1);
////    assertThat(actual).isEqualTo(new UserEntity(1, "○○○会社", "瀬川"));
////  }
}
