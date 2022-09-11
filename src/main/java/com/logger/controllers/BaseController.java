package com.logger.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseController {
    public long getUserId(){
        /*
         * In jwt token we keep the username as UserId
         * therefore we can get the user id from security context
         */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return  Long.parseLong(username);
    }
}
