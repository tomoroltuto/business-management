package com.example.businessmanagement2.restcontroller.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
public class UserDTO {

  long id;

  String companyname;

  String username;

}
