package com.example.businessmanagement2.controller.user;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;


@Data
public class UserListDTO {

  List<UserDTO> results = new ArrayList<>();

}
