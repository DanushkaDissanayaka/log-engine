package com.logger.base.config;

public interface AppConfiguration {

    String getJwtTokenSecret();
    Long getJwtTokenExpireTime();
    Long getJwtRefreshTokenExpireTime();
}
