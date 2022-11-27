package com.example.businessmanagement2.user;


import static org.assertj.core.api.Assertions.*;


import com.example.businessmanagement2.repository.user.UserEntity;
import com.example.businessmanagement2.repository.user.UserRepository;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;


@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

  @Autowired
  UserRepository userRepository;

  @Test
  @DataSet(value = "datasets/users.yml")
  @Transactional
  void すべてのユーザーが取得できること(){
    List<UserEntity> users = userRepository.findUserList();
    assertThat(users)
        .hasSize(2)
        .contains(
            new UserEntity(1L,"○○○会社", "瀬川"),
            new UserEntity(2L,"△△△会社", "瀬川2")
        );
  }

  @Test
  @DataSet(cleanBefore=true)
  @Transactional
  void ユーザーが登録いされてないときに空のリストが返ること(){
    List<UserEntity> users = userRepository.findUserList();
    assertThat(users).isEmpty();
  }

  @Test
  @DataSet(value = "datasets/users.yml")
  @Transactional
  void 存在するユーザのIDを指定してユーザーが取得できること(){
    UserEntity user = userRepository.findById(1).orElseThrow();
    assertThat(user).isEqualTo(new UserEntity(1L,"○○○会社", "瀬川"));
  }

  @Test
  @DataSet(value = "datasets/users.yml")
  @Transactional
  public void ユーザーを新規登録できること(){
    UserEntity ue = new UserEntity(null,"xxx会社", "瀬川3");
    userRepository.create(ue);
    List<UserEntity> actual = userRepository.findUserList();
    assertThat(actual).hasSize(3);
  }

  @Test
  @DataSet(value = "datasets/users.yml")
  @Transactional
  public void キーに紐づく1件の更新が出来ること(){
    userRepository.update(new UserEntity(1L, "xxx会社", "瀬川3"));
    Optional<UserEntity> user = userRepository.findById(1L);
    assertThat(user).hasValue(new UserEntity(1L, "xxx会社", "瀬川3"));
  }

  @Test
  @DataSet(value = "datasets/users.yml")
  @Transactional
  public void 指定したデーターを1件削除できること() {
    userRepository.delete(1L);
    List<UserEntity> actual = userRepository.findUserList();
    assertThat(actual).hasSize(1);
  }
}
