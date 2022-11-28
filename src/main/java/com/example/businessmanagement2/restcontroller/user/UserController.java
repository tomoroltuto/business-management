package com.example.businessmanagement2.restcontroller.user;

import com.example.businessmanagement2.repository.user.UserEntity;
import com.example.businessmanagement2.service.user.UserService;
import java.net.URI;
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
    public ResponseEntity<UserDTO> showUser(@PathVariable("id") Long userId){
        var entity = userService.findById(userId);
        var dto = toUserDTO(entity);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/users")
    private ResponseEntity<UserListDTO> findUserList(){
        var entityList = userService.findUserList();
        var dtoList = entityList
                        .stream()
                        .map(UserController::toUserDTO)
                        .collect(Collectors.toList());
        var dto = new UserListDTO();
        dto.setResults(dtoList);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/users")
    private ResponseEntity<UserResponseMessage> createUser(@RequestBody @Validated UserForm form){
        userService.create(form.getCompanyname(), form.getUsername());
        var urm = new UserResponseMessage();
        urm.setMessage("ユーザーを登録しました");
        return ResponseEntity.created(URI.create("/users")).body(urm);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<UserResponseMessage> updateUser(@PathVariable("id") Long userId, @RequestBody @Validated UserForm form) {
        userService.update(userId, form.getCompanyname(), form.getUsername());
        var urm = new UserResponseMessage();
        urm.setMessage("ユーザーを更新しました");
        return ResponseEntity.ok(urm);
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserResponseMessage> deleteUser(@PathVariable("id") Long userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}
