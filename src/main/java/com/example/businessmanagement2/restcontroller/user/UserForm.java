package com.example.businessmanagement2.restcontroller.user;

import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;


@Getter
@AllArgsConstructor
public class UserForm {


    @NotBlank
    @Size(min = 1, max = 256)
    String companyname;

    @NotBlank
    @Size(min = 1, max = 256)
    String username;
}
