package com.example.businessmanagement2.restcontroller.user;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class UserListDTO {


    private List<UserDTO> results = new ArrayList<>();

    public UserListDTO results(List<UserDTO> results) {
      this.results = results;
      return this;
    }

    public UserListDTO addResultsItem(UserDTO resultsItem) {
      this.results.add(resultsItem);
      return this;
    }



}
