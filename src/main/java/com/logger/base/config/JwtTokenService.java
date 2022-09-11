package com.logger.base.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;

public interface JwtTokenService {
    String generateJwtToken(User user);
    String generateJwtRefreshToken(User user);
    UsernamePasswordAuthenticationToken getAuthenticationToken(String authHeader);

}
