package com.example.businessmanagement2.service.user;

import com.example.businessmanagement2.repository.user.UserEntity;

import com.example.businessmanagement2.repository.user.UserRepository;
import java.util.List;

import com.example.businessmanagement2.restcontroller.user.UserResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;



    public UserEntity findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserEntityNotFoundException(userId));
    }
    public List<UserEntity> findUserList() {
        return userRepository.findUserList();
    }
    public void create(String companyname, String username) {
        var entity = new UserEntity(null, companyname, username);
        userRepository.create(entity);
    }

    @Override
    public UserEntity update(Long userId, String companyname, String username) {
        userRepository.findById(userId)
            .orElseThrow(() -> new UserEntityNotFoundException(userId));
        userRepository.update(new UserEntity(userId, companyname, username));
        return findById(userId);
    }

    @Override
    public void delete(Long userId) {
        userRepository.findById(userId)
            .orElseThrow(() -> new UserEntityNotFoundException(userId));
        userRepository.delete(userId);
    }

}
