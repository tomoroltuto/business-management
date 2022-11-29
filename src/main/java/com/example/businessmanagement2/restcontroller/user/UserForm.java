package com.example.businessmanagement2.restcontroller.user;


import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotBlank;



@Data
@AllArgsConstructor
public class UserForm {

    @NotBlank
    @Size(min = 1, max = 256)
    private String companyname;

    @NotBlank
    @Size(min = 1, max = 256)
    private String username;

}
