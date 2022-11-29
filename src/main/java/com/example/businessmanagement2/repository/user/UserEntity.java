package com.example.businessmanagement2.repository.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserEntity {

  Long id;

  String companyname;

  String username;
}
