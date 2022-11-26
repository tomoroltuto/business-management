package com.example.businessmanagement2.user;


import static org.assertj.core.api.Assertions.assertThat;

import com.example.businessmanagement2.repository.user.UserEntity;
import com.example.businessmanagement2.repository.user.UserRepository;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import java.util.List;
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

}
