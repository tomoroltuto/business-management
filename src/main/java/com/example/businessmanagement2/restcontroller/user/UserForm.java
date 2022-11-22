package com.example.businessmanagement2.restcontroller.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;


@Getter
@AllArgsConstructor
public class UserForm {


    @NotBlank
    @Length(min=1,max=256)
    String companyname;

    @NotBlank
    @Length(min=1,max=256)
    String username;
}
