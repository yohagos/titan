package com.titan.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class UserServiceTest {

    static UserEntity user1 = new UserEntity(10L, "test", "test", "test10@test.de", "test", 1111, LocalDateTime.now(), UserRole.USER);
    static UserEntity user2 = new UserEntity(20L, "test", "test", "test20@test.de", "test", 2222, LocalDateTime.now(), UserRole.USER);
    static UserEntity user3 = new UserEntity(30L, "test", "test", "test30@test.de", "test", 3333, LocalDateTime.now(), UserRole.USER);
    static UserEntity user4 = new UserEntity(40L, "test", "test", "test40@test.de", "test", 4444, LocalDateTime.now(), UserRole.USER);
    static UserEntity user5 = new UserEntity(50L, "test", "test", "test50@test.de", "test", 5555, LocalDateTime.now(), UserRole.USER);

    static List<UserEntity> userEntities = new ArrayList<>();

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @BeforeAll
    public static void setUpAll() {
        setUpUserList();
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("getUserList()")
    public void shouldReturnListOfFiveUsers() {
        when(userService.getUserList())
                .thenReturn(userEntities);
        when(userRepository.findAll())
                .thenReturn(userEntities);
        var userList = userService.getUserList();
        assertEquals(userEntities.size(), userList.size(), "Get user list error");
    }

    @Test
    @DisplayName("checkUserPin")
    public void shouldFindAUserDependingOnThePin() {
        when(userRepository.findUserByPin(user1.getPin()))
                .thenReturn(Optional.of(user1));
        var user = userService.checkUserPin(1111);
        assertEquals(user1, user, "optional user and test user are not equal");
    }

    private static void setUpUserList() {
        userEntities = List.of(user1,user2,user3,user4,user5);
    }
}