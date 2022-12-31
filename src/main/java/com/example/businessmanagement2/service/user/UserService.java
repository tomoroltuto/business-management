package com.example.businessmanagement2.service.user;

import com.example.businessmanagement2.repository.user.UserEntity;
import java.util.List;

public interface UserService {

  UserEntity findById(Long userId);

  List<UserEntity> findUserList();

  UserEntity create(String companyName, String userName);

  UserEntity update(Long userId, String companyName, String userName);

  void delete(Long userId);

}
