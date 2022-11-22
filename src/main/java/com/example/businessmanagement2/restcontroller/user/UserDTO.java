package com.example.businessmanagement2.restcontroller.user;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserDTO {

    long id;

    String companyname;

    String username;
}