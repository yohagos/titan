package com.titan.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("accessToken")
    private String accessToken;
    @JsonProperty("refreshToken")
    private String refreshToken;
    @JsonProperty("email")
    private String email;
}
