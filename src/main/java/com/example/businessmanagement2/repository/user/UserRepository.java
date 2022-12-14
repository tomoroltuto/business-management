package com.example.businessmanagement2.repository.user;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserRepository {

//  @Select("SELECT user_id, company_name, user_name FROM users WHERE user_id = #{userId}")
  Optional<UserEntity> findById(long userId);

//  @Select("SELECT * FROM users")
  List<UserEntity> findUserList();


//  @Options(useGeneratedKeys = true, keyProperty = "id")
//  @Insert("INSERT INTO users (company_name, user_name) values (#{companyname}, #{username})")
  void create(UserEntity entity);

//  @Update("UPDATE users SET company_name = #{companyname}, user_name = #{username} WHERE user_id = #{userId}")
  void update(UserEntity entity);

//  @Delete("DELETE from users WHERE user_id = #{userId}")
  void delete(Long userId);
}
