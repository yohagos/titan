package com.titan.user;

import com.titan.user.request.UserPinUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final static Logger log = LoggerFactory.getLogger(UserService.class);


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

    public UserEntity checkUserPin(Integer pin) {
        return userRepository.findUserByPin(pin).orElseThrow();
    }

    public Optional<UserEntity> findUserById(Long id) {
        return userRepository.findById(id);
    }
}
