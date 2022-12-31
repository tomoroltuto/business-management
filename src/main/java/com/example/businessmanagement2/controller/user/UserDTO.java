package com.example.businessmanagement2.controller.user;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserDTO {

  long userId;

  String companyName;

  String userName;

}
