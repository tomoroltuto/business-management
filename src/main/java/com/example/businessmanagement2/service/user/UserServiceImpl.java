package com.example.businessmanagement2.service.user;

import com.example.businessmanagement2.repository.user.UserEntity;
import com.example.businessmanagement2.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserEntity findById(long userId) {
        return userRepository.findById(userId).map(
                        record -> new UserEntity(record.getId(), record.getCompanyname(), record.getUsername()))
                .orElseThrow(() -> new UserEntityNotFoundException(userId));
    }
}
