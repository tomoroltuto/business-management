package com.example.businessmanagement2.restcontroller.user;

import com.example.businessmanagement2.repository.user.UserEntity;
import com.example.businessmanagement2.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private static UserDTO toUserDTO(UserEntity userEntity) {
        var userDTO = new UserDTO(userEntity.getId(), userEntity.getCompanyname(), userEntity.getUsername());
        userDTO.setId(userEntity.getId());
        userDTO.setCompanyname(userEntity.getCompanyname());
        userDTO.setUsername(userEntity.getUsername());
        return userDTO;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> showUser(@PathVariable("id") long userId){
        var entity = userService.findById(userId);
        var dto = toUserDTO(entity);
        return ResponseEntity.ok(dto);
    }


}
