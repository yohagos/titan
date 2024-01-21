package com.titan.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.titan.config.JwtAuthenticationFilter;
import com.titan.config.JwtService;
import com.titan.token.TokenRepository;
import com.titan.user.request.UserPinUpdateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Mock
    private TokenRepository tokenRepository;

    @Autowired
    private ObjectMapper objectMapper;

    UserEntity user1, user2, user3, user4, user5;

    List<UserEntity> userEntities = new ArrayList<>();

    @BeforeEach
    public void init() {
        user1 = new UserEntity(10L, "test", "test", "test10@test.de", "test", 1111, LocalDateTime.now(), UserRole.USER);
        user2 = new UserEntity(20L, "test", "test", "test20@test.de", "test", 2222, LocalDateTime.now(), UserRole.USER);
        user3 = new UserEntity(30L, "test", "test", "test30@test.de", "test", 3333, LocalDateTime.now(), UserRole.USER);
        user4 = new UserEntity(40L, "test", "test", "test40@test.de", "test", 4444, LocalDateTime.now(), UserRole.USER);
        user5 = new UserEntity(50L, "test", "test", "test50@test.de", "test", 5555, LocalDateTime.now(), UserRole.USER);

        userEntities = List.of(user1,user2,user3,user4,user5);
    }

    @Test
    @DisplayName("Get All Users")
    public void getAllUsersTest() throws Exception {
        given(userService.getUserList()).willReturn(userEntities);

        mockMvc.perform(
                get("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    @DisplayName("Get User with Pin")
    public void getUserDependingOnPin() throws Exception {
        given(userService.checkUserPin(user1.getPin())).willReturn(user1);

        mockMvc.perform(
                get("/api/v1/user/{pin}", user1.getPin())
        )
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("Update User Pin Test")
    public void updateUserPinTest() throws Exception {
        var uPin = new UserPinUpdateRequest(6666, 10L);
        given(userService.updateUserPin(uPin)).willReturn(user1);

        var gson = new Gson();

        mockMvc.perform(
                put("/api/v1/user/pin")
                        .content(gson.toJson(uPin))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    /*
    * Examples for different http method mappings
    *
    *   POST
    *
    *   @Test
        public void testCreateUser() throws Exception {
            User user = new User("John", "Doe");

            given(userService.createUser(user)).willReturn(user);

            mockMvc.perform(post("/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(user)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.firstName", is("John")))
                    .andExpect(jsonPath("$.lastName", is("Doe")));
        }
    *
    *
    *   PUT
    *   @Test
        public void testUpdateUser() throws Exception {
            User user = new User("John", "Doe");

            given(userService.updateUser(user)).willReturn(user);

            mockMvc.perform(put("/users/{id}", 1)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(user)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.firstName", is("John")))
                    .andExpect(jsonPath("$.lastName", is("Doe")));
        }
    *
    *
    *   DELETE
    *   @Test
        public void testDeleteUser() throws Exception {
            mockMvc.perform(delete("/users/{id}", 1))
                    .andExpect(status().isOk());
        }
    *
    * */
}