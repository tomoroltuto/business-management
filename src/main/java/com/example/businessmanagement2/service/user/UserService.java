package com.example.businessmanagement2.service.user;

import com.example.businessmanagement2.repository.user.UserEntity;
import java.util.List;

public interface UserService {

  UserEntity findById(Long userId);

  List<UserEntity> findAll();

  void create(String companyname, String username);

  UserEntity update(Long userId, String companyname, String username);

  void delete(Long userId);

}
