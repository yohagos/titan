package com.titan.token;

import com.titan.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TokenEntity {

    @Id
    @GeneratedValue
    public Long tokenId;
    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;
    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public UserEntity user;

    public TokenEntity(
            String token,
            boolean revoked,
            boolean expired,
            UserEntity user
    ) {
        this.token = token;
        this.tokenType = TokenType.BEARER;
        this.revoked = revoked;
        this.expired = expired;
        this.user = user;
    }

}
