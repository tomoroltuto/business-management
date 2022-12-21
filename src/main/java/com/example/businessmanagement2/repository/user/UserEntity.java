package com.example.businessmanagement2.repository.user;


import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class UserEntity {

  Long userid;

  String companyname;

  String username;

}
