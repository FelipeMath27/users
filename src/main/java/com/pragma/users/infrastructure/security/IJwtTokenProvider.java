package com.pragma.users.infrastructure.security;

import com.pragma.users.domain.model.User;
import io.jsonwebtoken.Claims;

public interface IJwtTokenProvider {
    String generateToken(User user);
    boolean isTokenValid(String token);
    Claims getClaims(String token);
}
