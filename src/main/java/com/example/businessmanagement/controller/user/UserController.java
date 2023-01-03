package com.example.businessmanagement.controller.user;

import com.example.businessmanagement.repository.user.UserEntity;
import com.example.businessmanagement.service.user.UserService;
import java.net.URI;
import java.util.ArrayList;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  private static UserDTO toUserDTO(UserEntity userEntity) {
    var userDTO = new UserDTO(userEntity.getUserId(), userEntity.getCompanyName(),
        userEntity.getUserName());
    return userDTO;
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<UserDTO> showUser(@PathVariable("id") Long userId) {
    var entity = userService.findById(userId);
    var dto = toUserDTO(entity);
    return ResponseEntity.ok(dto);
  }

  @GetMapping("/users")
  private ResponseEntity<UserListDTO> findUserList() {
    var entityList = userService.findUserList();
    var dtoList = entityList.stream().map(UserController::toUserDTO).collect(Collectors.toList());
    var dto = new UserListDTO(new ArrayList<>(dtoList));
    return ResponseEntity.ok(dto);
  }

  @PostMapping("/users")
  private ResponseEntity<UserResponseMessage> createUser(@RequestBody @Validated UserForm form,
      UriComponentsBuilder uriBuilder) {
    UserEntity ur = userService.create(form.getCompanyName(), form.getUserName());
    URI url = uriBuilder.path("users/" + ur.getUserId()).build().toUri();
    var urm = new UserResponseMessage("ユーザーを登録しました");
    return ResponseEntity.created(url).body(urm);
  }


  @PatchMapping("/users/{id}")
  public ResponseEntity<UserResponseMessage> updateUser(@PathVariable("id") Long userId,
      @RequestBody @Validated UserForm form) {
    userService.update(userId, form.getCompanyName(), form.getUserName());
    var urm = new UserResponseMessage("ユーザーを更新しました");
    return ResponseEntity.ok(urm);
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<UserResponseMessage> deleteUser(@PathVariable("id") Long userId) {
    userService.delete(userId);
    return ResponseEntity.noContent().build();
  }
}
