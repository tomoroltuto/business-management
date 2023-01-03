package com.example.businessmanagement.controller.user;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class UserDTO {

  long userId;

  String companyName;

  String userName;

}
