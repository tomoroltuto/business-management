package com.example.businessmanagement2.repository.user;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {

  Optional<UserEntity> findById(long userId);

  List<UserEntity> findUserList();

  void create(UserEntity entity);

  void update(UserEntity entity);

  void delete(Long userId);
}

