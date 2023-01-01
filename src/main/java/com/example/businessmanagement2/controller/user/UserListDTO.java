package com.example.businessmanagement2.controller.user;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class UserListDTO {

  List<UserDTO> results;

}

