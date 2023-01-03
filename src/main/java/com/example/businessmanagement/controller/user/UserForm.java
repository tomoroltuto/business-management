package com.example.businessmanagement.controller.user;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class UserForm {

  @NotBlank
  @Size(min = 1, max = 256)
  private String companyName;

  @NotBlank
  @Size(min = 1, max = 256)
  private String userName;

}

