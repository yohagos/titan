package com.titan.user;

import com.titan.user.request.UserAddRequest;
import com.titan.user.request.UserEditRequest;
import com.titan.user.request.UserPinUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return ResponseEntity.ok(userService.getUserList());
    }

    @GetMapping("/{pin}")
    public ResponseEntity<UserEntity> checkUser(
            @PathVariable(name = "pin") Integer pin
    ) {
        return ResponseEntity.ok(userService.checkUserPin(pin));
    }

    @PostMapping("/add")
    public ResponseEntity<UserEntity> addNewUser(
            @RequestBody UserAddRequest request
    ) {
        return ResponseEntity.ok(userService.addUser(request));
    }

    @PutMapping("/edit")
    public ResponseEntity<UserEntity> editUser(
            @RequestBody UserEditRequest request
    ) {
        return ResponseEntity.ok(userService.editUser(request));
    }

    @PutMapping("/pin")
    public ResponseEntity<UserEntity> updatePin(
            @RequestBody UserPinUpdateRequest request
    ) {
        return ResponseEntity.ok(userService.updateUserPin(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserEntity> deleteUserById(
        @PathVariable(name = "id") Long id
    ) {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }
}
