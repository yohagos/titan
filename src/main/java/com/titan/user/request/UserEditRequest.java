package com.titan.user.request;

import com.titan.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEditRequest {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Integer pin;
    private String role;

}
