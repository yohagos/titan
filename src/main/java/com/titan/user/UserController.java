package com.titan.user;

import com.titan.user.request.UserPinUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return ResponseEntity.ok(userService.getUserList());
    }

    @GetMapping("/{pin}")
    public ResponseEntity<UserEntity> checkUser(
            @PathVariable(name = "pin") Integer pin
    ) {
        log.info(pin.toString());
        return ResponseEntity.ok(userService.checkUserPin(pin));
    }

    @PutMapping("/pin")
    public ResponseEntity<?> updatePin(
            @RequestBody UserPinUpdateRequest request
    ) {
        userService.updateUserPin(request);
        return ResponseEntity.ok().build();
    }
}
