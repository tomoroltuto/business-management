package com.example.businessmanagement.service.user;

import com.example.businessmanagement.repository.user.UserEntity;
import java.util.List;

public interface UserService {

  UserEntity findById(Long userId);

  List<UserEntity> findUserList();

  UserEntity create(String companyName, String userName);

  void update(Long userId, String companyName, String userName);

  void delete(Long userId);

}

