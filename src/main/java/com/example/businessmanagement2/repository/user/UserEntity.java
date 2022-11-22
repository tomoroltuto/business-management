package com.example.businessmanagement2.repository.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserEntity {

    long id;

    String companyname;

    String username;
}
