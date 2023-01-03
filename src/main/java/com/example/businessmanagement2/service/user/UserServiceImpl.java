package com.example.businessmanagement2.service.user;

import com.example.businessmanagement2.repository.user.UserEntity;
import com.example.businessmanagement2.repository.user.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;


  public UserEntity findById(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new UserEntityNotFoundException(userId));
  }

  public List<UserEntity> findUserList() {
    return userRepository.findUserList();
  }

  public UserEntity create(String companyName, String userName) {
    var entity = new UserEntity(null, companyName, userName);
    userRepository.create(entity);
    return entity;
  }

  @Override
  public void update(Long userId, String companyName, String userName) {
    userRepository.findById(userId).orElseThrow(() -> new UserEntityNotFoundException(userId));
    userRepository.update(new UserEntity(userId, companyName, userName));
  }

  @Override
  public void delete(Long userId) {
    userRepository.findById(userId).orElseThrow(() -> new UserEntityNotFoundException(userId));
    userRepository.delete(userId);
  }
}
