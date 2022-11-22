package com.example.businessmanagement2.repository.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Data
@AllArgsConstructor
public class UserEntity {

    Long id;

    String companyname;

    String username;
}
