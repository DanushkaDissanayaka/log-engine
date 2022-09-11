package com.logger.config;

import com.logger.base.config.AppConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AppConfigurationImpl implements AppConfiguration {
    @Value("${spring.security.jwt.sign.key}")
    private String jwtTokenSecret;
    @Value("${spring.security.jwt.token.expiretime.hours}")
    private Long jwtTokenExpireTime;
    @Value("${spring.security.jwt.refresh.token.expiretime.days}")
    private Long jwtRefreshTokenExpireTime;

    public String getJwtTokenSecret() {
        return jwtTokenSecret;
    }

    public Long getJwtTokenExpireTime() {
        return jwtTokenExpireTime;
    }

    public Long getJwtRefreshTokenExpireTime() {
        return jwtRefreshTokenExpireTime;
    }
}
