package com.titan.user;

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

    @PutMapping("/pin")
    public ResponseEntity<?> updatePin(
            @RequestBody UserPinUpdateRequest request
    ) {
        userService.updateUserPin(request);
        return ResponseEntity.ok().build();
    }
}
