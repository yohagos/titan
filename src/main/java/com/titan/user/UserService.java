package com.titan.user;

import com.titan.user.request.UserAddRequest;
import com.titan.user.request.UserEditRequest;
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

    public UserEntity updateUserPin(UserPinUpdateRequest request) {
        var user = userRepository.findById(request.getUserId()).orElseThrow();
        if (request.getUserId() == null || (user.getPin() != null && request.getPin().equals(user.getPin())))
            throw new IllegalArgumentException("Pin of User " + user.getUsername() + " cannot be changes");
        if (String.valueOf(request.getPin()).length() != 4)
            throw new IllegalArgumentException("Length of Pin has to be 4 digits");
        user.setPin(request.getPin());
        userRepository.save(user);

        return user;
    }

    public UserEntity checkUserPin(Integer pin) {
        return userRepository.findUserByPin(pin).orElseThrow();
    }

    public Optional<UserEntity> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public UserEntity deleteUserById(Long id) {
        var user = userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
        return user;
    }

    public UserEntity addUser(UserAddRequest request) {
        var user = userRepository.findUserByEmail(request.getEmail());
        if (user.isPresent())
            throw new IllegalArgumentException("email already in use");

        user = userRepository.findUserByPin(request.getPin());
        if (user.isPresent())
            throw new IllegalArgumentException("pin already in use");

        UserEntity newUser = new UserEntity();
        newUser.setFirstname(request.getFirstname());
        newUser.setLastname(request.getLastname());
        newUser.setEmail(request.getEmail());
        newUser.setPin(request.getPin());
        var role = UserRole.valueOf(request.getRole().toUpperCase());
        newUser.setRole(role);
        return userRepository.save(newUser);
    }

    public UserEntity editUser(UserEditRequest request) {
        var user = userRepository.findById(request.getId()).orElseThrow();

        var role = UserRole.valueOf(request.getRole().toUpperCase());

        Optional.ofNullable(request.getFirstname())
                .filter(
                        firstname -> !firstname.isEmpty() || !firstname.contentEquals(user.getFirstname())
                ).ifPresent(user::setFirstname);
        Optional.ofNullable(request.getLastname())
                .filter(
                        name -> !name.isEmpty() || !name.contentEquals(user.getLastname())
                ).ifPresent(user::setLastname);
        Optional.ofNullable(request.getEmail())
                .filter(
                        email -> !email.isEmpty() || !email.contentEquals(user.getEmail())
                ).ifPresent(user::setEmail);
        Optional.ofNullable(request.getPin())
                .filter(
                        pin -> pin != user.getPin()
                ).ifPresent(user::setPin);
        Optional.of(role)
                .filter(
                        r -> !r.equals(user.getRole())
                ).ifPresent(user::setRole);
        var response = userRepository.save(user);
        return response;
    }
}
