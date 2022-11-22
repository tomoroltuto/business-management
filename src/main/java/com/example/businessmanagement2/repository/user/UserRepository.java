package com.example.businessmanagement2.repository.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface UserRepository {

    @Select("SELECT id,company_name, user_name FROM users WHERE id = #{userId}")
    Optional<UserEntity> findById(long userId);

}
