package com.titan.user.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String role;
    private Integer pin;
}
