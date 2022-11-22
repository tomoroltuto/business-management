package com.example.businessmanagement2.service.user;

import com.example.businessmanagement2.repository.user.UserEntity;


public interface UserService {

    UserEntity findById(long userId);
}
