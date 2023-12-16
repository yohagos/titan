package com.titan.user;

import com.titan.user.request.UserPinUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public List<UserEntity> getUserList() {
        return userRepository.findAll();
    }

    public void updateUserPin(UserPinUpdateRequest request) {
        var user = userRepository.findById(request.getUserId()).orElseThrow();
        if (request.getUserId() == null || (user.getPin() != null && request.getPin() == user.getPin()))
            throw new IllegalArgumentException("Pin of User " + user.getUsername() + " cannot be changes");
        if (String.valueOf(request.getPin()).length() != 4)
            throw new IllegalArgumentException("Length of Pin has to be 4 digits");
        user.setPin(request.getPin());
        userRepository.save(user);
    }
}
